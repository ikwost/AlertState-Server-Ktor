package com.ikwost.plugins

import com.ikwost.domain.MapRoomController
import com.ikwost.domain.repository.LocationDataSource
import com.ikwost.domain.repository.UserDataSource
import com.ikwost.routes.*
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import org.koin.java.KoinJavaComponent
import org.koin.ktor.ext.inject

fun Application.configureRouting() {

    routing {
        val userDataSource: UserDataSource by KoinJavaComponent.inject(UserDataSource::class.java)
        val locationDataSource: LocationDataSource by KoinJavaComponent.inject(LocationDataSource::class.java)
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

