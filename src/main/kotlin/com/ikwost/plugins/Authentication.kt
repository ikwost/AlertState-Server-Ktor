package com.ikwost.plugins

import com.ikwost.domain.model.Endpoint
import com.ikwost.domain.model.UserSession
import com.ikwost.util.Constants.AUTHENTICATION_PROVIDER_NAME
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*

fun Application.configureAuth() {
    install(Authentication) {
        session<UserSession>(name = AUTHENTICATION_PROVIDER_NAME) {
            validate { session ->
                session
            }
            challenge {
                call.respondRedirect(Endpoint.Unauthorized.path)
            }
        }
    }
}