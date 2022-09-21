package com.ikwost.plugins

import com.ikwost.domain.model.UserSession
import io.ktor.server.application.*
import io.ktor.server.sessions.*
import io.ktor.util.*
import java.io.File

fun Application.configureSession() {
    install(Sessions) {
        val secretEncryptKey = hex("00112233445566778899aabbccddeeff")
        val secretAuthKey = hex("0203040506070809a0b0c")
        cookie<UserSession>(
            name = "USER_SESSION",
            storage = directorySessionStorage(File(".sessions"))
        ) {
            // cookie.maxAge // TODO
            cookie.secure = false //Change to "true" in real project
            transform(SessionTransportTransformerEncrypt(secretEncryptKey, secretAuthKey))
        }
    }
}