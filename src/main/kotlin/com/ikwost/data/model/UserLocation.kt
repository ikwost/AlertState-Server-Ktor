package com.ikwost.data.model

import com.ikwost.domain.model.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.litote.kmongo.id.MongoId

@Serializable
data class UserLocation(
    val lat: Long,
    val lng: Long,
    val userId: String
)
