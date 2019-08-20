package com.rockycamacho.willow.testapp.di

import com.rockycamacho.willow.testapp.BuildConfig
import com.rockycamacho.willow.testapp.di.OpenForTesting
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import javax.inject.Singleton

@OpenForTesting
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun callAdapterFactory(): CallAdapter.Factory = RxJava2CallAdapterFactory.create()

    @Singleton
    @Provides
    fun converterFactory(moshi: Moshi): Converter.Factory = MoshiConverterFactory.create(moshi)

    @Singleton
    @Provides
    fun level(): HttpLoggingInterceptor.Level =
        when (BuildConfig.DEBUG) {
            true -> HttpLoggingInterceptor.Level.BODY
            else -> HttpLoggingInterceptor.Level.NONE
        }

    @Singleton
    @Provides
    fun httpLoggingInterceptor(level: HttpLoggingInterceptor.Level): HttpLoggingInterceptor =
        HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Timber.d(message) })
            .setLevel(level)

}
