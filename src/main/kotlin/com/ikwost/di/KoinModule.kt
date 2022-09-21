package com.ikwost.di

import com.ikwost.data.repository.UserDataSourceImpl
import com.ikwost.domain.repository.UserDataSource
import com.ikwost.util.Constants.DATABASE_NAME
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val koinModule = module {
    single {
        KMongo.createClient()
            .coroutine
            .getDatabase(DATABASE_NAME)
    }
    single<UserDataSource> {
        UserDataSourceImpl(get())
    }
}