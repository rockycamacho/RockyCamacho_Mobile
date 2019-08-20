package com.rockycamacho.willow.testapp

import android.app.Application
import android.content.Context
import com.rockycamacho.willow.testapp.di.*
import com.ww.roxie.Roxie
import timber.log.Timber

class WillowTestApp : Application() {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        buildAppComponent()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Roxie.enableLogging(object : Roxie.Logger {
                override fun log(msg: String) {
                    Timber.tag("Roxie").d(msg)
                }
            })
        }
    }

    private fun buildAppComponent() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .dataModule(DataModule())
            .networkModule(NetworkModule())
            .apiModule(ApiModule(BuildConfig.API_ENDPOINT))
            .build()
    }

    fun setAppComponent(component: AppComponent) {
        appComponent = component
    }

    fun getAppComponent(): AppComponent = appComponent

    companion object {
        operator fun get(context: Context): WillowTestApp =
            context.applicationContext as WillowTestApp
    }
}