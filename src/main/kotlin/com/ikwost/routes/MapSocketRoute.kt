package com.ikwost.routes

import com.ikwost.data.model.UserLocation
import com.ikwost.domain.MapRoomController
import com.ikwost.domain.model.ApiResponse
import com.ikwost.domain.model.Endpoint
import com.ikwost.domain.model.UserSession
import com.ikwost.domain.repository.UserDataSource
import com.ikwost.util.Constants.AUTHENTICATION_PROVIDER_NAME
import com.ikwost.util.MemberAlreadyExistsException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.channels.consumeEach

fun Route.mapSocket(
    app: Application,
    mapRoomController: MapRoomController,
    userDataSource: UserDataSource
) {
    authenticate(AUTHENTICATION_PROVIDER_NAME) {
        //TODO to Constant
        webSocket(Endpoint.MapWebSocket.path) {
            val userSession = call.principal<UserSession>()
            if (userSession == null) {
                close()
                app.log.info("INVALID SESSION")
                call.respondRedirect(Endpoint.Unauthorized.path)
            } else {
                try {
                    mapRoomController.onJoinMapRoom(
                        username = userSession.name,
                        sessionId = userSession.id,
                        socket = this
                    )
                    call.respond(
                        message = ApiResponse(
                            success = true,
                            user = userDataSource.getUserInfo(userId = userSession.id)
                        ),
                        status = HttpStatusCode.OK
                    )

                    incoming.consumeEach { frame ->
                        if (frame is Frame.Text) {
                            mapRoomController.sendLocation(
                                userId = userSession.id,
                                location = receiveDeserialized<UserLocation>()
                            )
                        }
                    }

                } catch (e: MemberAlreadyExistsException) {
                    call.respond(HttpStatusCode.Conflict)
                    call.respondRedirect(Endpoint.Unauthorized.path)
                } catch (e: Exception) {
                    app.log.info("GETTING WEBSOCKET ERROR: ${e.message}")
                    call.respondRedirect(Endpoint.Unauthorized.path)
                } finally {
                    mapRoomController.tryDisconnect(userSession.name)
                }
            }
        }
    }
}

fun Route.getAllLocations(
    app: Application,
    mapRoomController: MapRoomController
) {
    authenticate(AUTHENTICATION_PROVIDER_NAME) {
        //TODO to Constant
        get(Endpoint.GetAllLocations.path) {
            val userSession = call.principal<UserSession>()
            if (userSession == null) {
                app.log.info("INVALID SESSION")
                call.respondRedirect(Endpoint.Unauthorized.path)
            } else {
                try {
                    call.respond(
                        message = ApiResponse(
                            success = true,
                            locations = mapRoomController.getAllLocations()
                        ),
                        status = HttpStatusCode.OK
                    )

                } catch (e: Exception) {
                    app.log.info("GETTING GET ALL LOCATIONS ERROR: ${e.message}")
                    call.respondRedirect(Endpoint.Unauthorized.path)
                }
            }
        }
    }
}