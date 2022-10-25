package com.ikwost.di

import com.ikwost.data.repository.LocationDataSourceImpl
import com.ikwost.data.repository.UserDataSourceImpl
import com.ikwost.domain.MapRoomController
import com.ikwost.domain.repository.LocationDataSource
import com.ikwost.domain.repository.UserDataSource
import com.ikwost.util.Constants.DATABASE_LOCATION
import com.ikwost.util.Constants.DATABASE_USER
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val koinModule = module {
    single {
        KMongo.createClient()
            .coroutine
            .getDatabase(DATABASE_USER)
    }
    single<UserDataSource> {
        UserDataSourceImpl(get())
    }
   }
val koinModule2 = module {
    single {
        KMongo.createClient()
            .coroutine
            .getDatabase(DATABASE_LOCATION)
    }
    single<LocationDataSource> {
        LocationDataSourceImpl(get())
    }
    single {
        MapRoomController(get())
    }
}