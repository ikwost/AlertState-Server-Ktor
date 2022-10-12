package com.ikwost.data.model

import com.ikwost.domain.model.User
import kotlinx.serialization.Serializable

@Serializable
data class UserLocation(
    val lat: Long,
    val lng: Long,
    val userId: String
)
