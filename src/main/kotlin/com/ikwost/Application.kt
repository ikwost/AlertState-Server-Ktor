package com.ikwost

import io.ktor.server.application.*
import com.ikwost.plugins.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    configureKoin()
    configureSockets()
    configureRouting()
    configureAuth()
    configureSerialization()
    configureMonitoring()
    configureRouting()
    configureSession()

}
