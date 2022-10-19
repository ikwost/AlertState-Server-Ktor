package com.ikwost.domain

import com.ikwost.data.model.UserLocation
import com.ikwost.domain.model.Member
import com.ikwost.domain.repository.LocationDataSource
import com.ikwost.util.MemberAlreadyExistsException
import io.ktor.websocket.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
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

    suspend fun sendLocation(userId: String, location: UserLocation) {

        members.values.forEach { member ->
            val userLocationEntity = UserLocation(
                lat = location.lat,
                lng = location.lng,
                userId = userId
            )
            locationDataSource.insertLocation(userLocationEntity)

            val parsedLocation = Json.encodeToString(userLocationEntity)
            member.socket.send(Frame.Text(parsedLocation))
        }
    }

    suspend fun getAllLocations(): List<UserLocation> {
        return locationDataSource.gelAllLocations()
    }

    suspend fun tryDisconnect(username: String) {
        members[username]?.socket?.close()
        if (members.containsKey(username)) {
            members.remove(username)
        }
    }
}
