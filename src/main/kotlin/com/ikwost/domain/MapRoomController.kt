package com.ikwost.domain

import com.ikwost.domain.model.Member
import com.ikwost.domain.repository.LocationDataSource
import io.ktor.websocket.*
import java.util.concurrent.ConcurrentHashMap

class MapRoomController(
    private val locationDataSource: LocationDataSource
) {
    private val members = ConcurrentHashMap<String, Member>()


    fun onJoin(
        username: String,
        sessionId: String,
        socket: WebSocketSession
    ) {

    }
}