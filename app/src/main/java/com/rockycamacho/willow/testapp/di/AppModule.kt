package com.rockycamacho.willow.testapp.di

import android.app.Application
import com.rockycamacho.willow.testapp.di.OpenForTesting
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@OpenForTesting
@Module
class AppModule(private val app: Application) {

    @Singleton
    @Provides
    fun application(): Application = app

}
