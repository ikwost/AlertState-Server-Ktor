package com.ikwost.routes

import com.ikwost.domain.model.ApiResponse
import com.ikwost.domain.model.Endpoint
import com.ikwost.domain.model.UserSession
import com.ikwost.util.Constants
import com.ikwost.util.Constants.AUTHENTICATION_PROVIDER_NAME
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*

fun Route.signOutRoute() {
    authenticate(AUTHENTICATION_PROVIDER_NAME) {
        get(Endpoint.SignOut.path) {
            call.sessions.clear<UserSession>()
            call.respond(
                message = ApiResponse(success = true),
                status = HttpStatusCode.OK
            )
        }
    }
}