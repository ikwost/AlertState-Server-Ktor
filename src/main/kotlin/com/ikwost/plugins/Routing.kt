package com.ikwost.plugins

import com.ikwost.di.USER_QUALIFIER
import com.ikwost.domain.MapRoomController
import com.ikwost.domain.repository.LocationDataSource
import com.ikwost.domain.repository.UserDataSource
import com.ikwost.routes.*
import io.ktor.server.routing.*
import io.ktor.server.application.*
import org.koin.core.qualifier.named
import org.koin.java.KoinJavaComponent
import org.koin.ktor.ext.inject

fun Application.configureRouting() {

    routing {
        val userDataSource by inject<UserDataSource>(qualifier = named(USER_QUALIFIER))
        val mapRoomController by inject<MapRoomController>()


        rootRoute()
        tokenVerificationRoute(application, userDataSource)
        mapSocket(application,mapRoomController,userDataSource)
        getAllLocations(application,mapRoomController)
        getUserInfoRoute(application, userDataSource)
        updateUserRoute(application, userDataSource)
        deleteUserRoute(application, userDataSource)
        signOutRoute()
        authorizedRoute()
        unauthorizedRoute()
    }
}

