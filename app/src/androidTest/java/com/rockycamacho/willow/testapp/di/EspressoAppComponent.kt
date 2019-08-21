package com.rockycamacho.willow.testapp.di

import com.rockycamacho.willow.testapp.endtoend.SelectFilterBuildingTest
import com.rockycamacho.willow.testapp.endtoend.ViewBuildingTest
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        EspressoAppModule::class,
        DataModule::class,
        NetworkModule::class,
        ApiModule::class
    ]
)
interface EspressoAppComponent : AppComponent {
    fun inject(test: ViewBuildingTest)
    fun inject(test: SelectFilterBuildingTest)

}
