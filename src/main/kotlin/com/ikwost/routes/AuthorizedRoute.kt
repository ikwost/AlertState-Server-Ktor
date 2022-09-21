package com.ikwost.routes

import com.ikwost.domain.model.ApiResponse
import com.ikwost.domain.model.Endpoint
import com.ikwost.util.Constants
import com.ikwost.util.Constants.AUTHENTICATION_PROVIDER_NAME
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.authorizedRoute() {
    authenticate(AUTHENTICATION_PROVIDER_NAME) {
        get(Endpoint.Authorized.path) {
            call.respond(
                message = ApiResponse(success = true),
                status = HttpStatusCode.OK
            )
        }
    }
}