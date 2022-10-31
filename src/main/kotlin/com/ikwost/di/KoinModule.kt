package com.ikwost.di

import com.ikwost.data.repository.LocationDataSourceImpl
import com.ikwost.data.repository.UserDataSourceImpl
import com.ikwost.domain.MapRoomController
import com.ikwost.domain.repository.LocationDataSource
import com.ikwost.domain.repository.UserDataSource
import com.ikwost.util.Constants.DATABASE_LOCATION
import com.ikwost.util.Constants.DATABASE_USER
import com.ikwost.util.Constants.LOCATION_QUALIFIER
import com.ikwost.util.Constants.USER_QUALIFIER
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val koinModule = module {
    single(named(USER_QUALIFIER)) {
        KMongo.createClient()
            .coroutine
            .getDatabase(DATABASE_USER)
    }
    single(named(LOCATION_QUALIFIER)) {
        KMongo.createClient()
            .coroutine
            .getDatabase(DATABASE_LOCATION)
    }
    single<UserDataSource>(qualifier = named(USER_QUALIFIER)) {
        UserDataSourceImpl(get(qualifier = named(USER_QUALIFIER)))
    }

    single<LocationDataSource>(qualifier = named(LOCATION_QUALIFIER)) {
        LocationDataSourceImpl(get(qualifier = named(LOCATION_QUALIFIER)))
    }
    single {
        MapRoomController(get(qualifier = named(LOCATION_QUALIFIER)))
    }
}

