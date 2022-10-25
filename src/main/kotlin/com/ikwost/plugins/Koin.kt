package com.ikwost.plugins

import com.ikwost.di.koinModule
import com.ikwost.di.koinModule2
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin

fun Application.configureKoin() {
    install(Koin) {
        modules(koinModule, koinModule2)
    }
}