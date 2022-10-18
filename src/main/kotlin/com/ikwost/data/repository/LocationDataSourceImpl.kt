package com.ikwost.data.repository

import com.ikwost.data.model.UserLocation
import com.ikwost.domain.repository.LocationDataSource
import org.litote.kmongo.coroutine.CoroutineDatabase

class LocationDataSourceImpl(
    private val db: CoroutineDatabase
) : LocationDataSource {

    private val userLocations = db.getCollection<UserLocation>()
    override suspend fun gelAllLocations(): List<UserLocation> {
        return userLocations.find()
            .toList()
    }

    override suspend fun insertLocation(userLocation: UserLocation) {
        userLocations.insertOne(userLocation)
    }

    override suspend fun replaceLocation(userId: String, userLocation: UserLocation) {
        userLocations.replaceOneById(id = userId, replacement = userLocation)
    }
}