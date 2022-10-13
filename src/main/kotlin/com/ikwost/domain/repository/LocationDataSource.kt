package com.ikwost.domain.repository

import com.ikwost.data.model.UserLocation
import com.ikwost.domain.model.User

interface LocationDataSource {

    suspend fun gelAllLocations(): Map<User,UserLocation>

    suspend fun insertLocation(userLocation: UserLocation)
}