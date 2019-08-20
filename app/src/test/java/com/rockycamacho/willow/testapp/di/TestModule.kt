package com.rockycamacho.willow.testapp.di

import com.github.javafaker.Faker
import dagger.Module
import dagger.Provides
import java.io.File
import javax.inject.Named
import javax.inject.Singleton

@OpenForTesting
@Module
class TestModule {

    @Singleton
    @Provides
    fun faker(): Faker = Faker()

    @Singleton
    @Provides
    @Named("http-cache")
    fun cache(): File {
        return File("build/http-cache")
    }

}
