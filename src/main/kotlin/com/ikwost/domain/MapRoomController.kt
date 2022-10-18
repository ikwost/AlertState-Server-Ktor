package com.ikwost.domain

import com.ikwost.data.model.UserLocation
import com.ikwost.domain.model.Member
import com.ikwost.domain.repository.LocationDataSource
import com.ikwost.util.MemberAlreadyExistsException
import io.ktor.websocket.*
import java.util.concurrent.ConcurrentHashMap

class MapRoomController(
    private val locationDataSource: LocationDataSource
) {
    private val members = ConcurrentHashMap<String, Member>()


    fun onJoinMapRoom(
        username: String,
        sessionId: String,
        socket: WebSocketSession
    ) {
        if (members.containsKey(username)) {
            throw MemberAlreadyExistsException()
        }
        members[username] = Member(
            username = username,
            sessionId = sessionId,
            socket = socket
        )
    }

    fun sendLocation(userId: String, location: UserLocation) {
        val userLocationEntity = UserLocation(
            lat = location.lat,
            lng = location.lng,
            userId = userId
        )
    }
}
