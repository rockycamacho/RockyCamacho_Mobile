package com.rockycamacho.willow.testapp.di

import com.rockycamacho.willow.testapp.data.network.ApiService
import com.rockycamacho.willow.testapp.di.OpenForTesting
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@OpenForTesting
@Module
class ApiModule(private val apiEndpoint: String) {

    @Singleton
    @Provides
    fun apiService(
        okHttpClient: OkHttpClient,
        callAdapterFactory: CallAdapter.Factory,
        converterFactory: Converter.Factory
    ): ApiService =
        Retrofit.Builder()
            .baseUrl(apiEndpoint)
            .addCallAdapterFactory(callAdapterFactory)
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)

    @Singleton
    @Provides
    fun okHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addNetworkInterceptor(httpLoggingInterceptor) //NOTE: should always be the last interceptor
            .build()

}
