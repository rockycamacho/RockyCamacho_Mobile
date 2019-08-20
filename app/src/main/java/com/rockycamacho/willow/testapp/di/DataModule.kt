package com.rockycamacho.willow.testapp.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@OpenForTesting
@Module
class DataModule {

    @Singleton
    @Provides
    fun moshi(): Moshi = Moshi.Builder()
        .build()

}
