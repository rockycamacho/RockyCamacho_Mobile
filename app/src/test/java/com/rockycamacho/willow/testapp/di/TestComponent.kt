package com.rockycamacho.willow.testapp.di

import com.rockycamacho.willow.testapp.data.network.GetBuildingsApiTest
import com.rockycamacho.willow.testapp.data.network.GetUserApiTest
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    TestModule::class,
    DataModule::class,
    NetworkModule::class,
    ApiModule::class
])
interface TestComponent {
    fun inject(test: GetBuildingsApiTest)
    fun inject(test: GetUserApiTest)

}