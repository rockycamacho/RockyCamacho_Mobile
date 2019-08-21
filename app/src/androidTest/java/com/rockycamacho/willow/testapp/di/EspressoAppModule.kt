package com.rockycamacho.willow.testapp.di

import android.app.Application
import com.github.javafaker.Faker
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@OpenForTesting
@Module
class EspressoAppModule(private val app: Application) {

    @Singleton
    @Provides
    fun application(): Application = app

    @Singleton
    @Provides
    fun faker(): Faker = Faker()

}