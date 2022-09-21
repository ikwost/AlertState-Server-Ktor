package com.ikwost.plugins

import com.ikwost.domain.repository.UserDataSource
import com.ikwost.routes.*
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import org.koin.java.KoinJavaComponent

fun Application.configureRouting() {

    routing {
        val userDataSource: UserDataSource by KoinJavaComponent.inject(UserDataSource::class.java)

        rootRoute()
        tokenVerificationRoute(application, userDataSource)
        getUserInfoRoute(application, userDataSource)
        updateUserRoute(application, userDataSource)
        deleteUserRoute(application, userDataSource)
        signOutRoute()
        authorizedRoute()
        unauthorizedRoute()
    }
    }
}
