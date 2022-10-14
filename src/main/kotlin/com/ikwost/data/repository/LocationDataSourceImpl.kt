package com.ikwost.data.repository

import com.ikwost.data.model.UserLocation
import com.ikwost.domain.model.User
import com.ikwost.domain.repository.LocationDataSource

class LocationDataSourceImpl:LocationDataSource {
    override suspend fun gelAllLocations(): Map<User, UserLocation> {
        TODO("Not yet implemented")
    }

    override suspend fun insertLocation(userLocation: UserLocation) {
        TODO("Not yet implemented")
    }
}