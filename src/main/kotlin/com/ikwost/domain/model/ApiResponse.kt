package com.ikwost.domain.model

import com.ikwost.data.model.UserLocation
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val success: Boolean,
    val user: User? = null,
    val message: String? = null,
    val locations: List<UserLocation>? = null
)
