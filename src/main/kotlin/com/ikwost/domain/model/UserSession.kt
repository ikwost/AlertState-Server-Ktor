package com.ikwost.domain.model

import io.ktor.server.auth.*
import kotlinx.serialization.Serializable


data class UserSession(
    val id: String,
    val name: String
): Principal
