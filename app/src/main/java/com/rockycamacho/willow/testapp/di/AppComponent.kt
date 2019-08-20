package com.rockycamacho.willow.testapp.di

import com.rockycamacho.willow.testapp.presentation.MainActivity
import com.rockycamacho.willow.testapp.presentation.buildings.list.ListBuildingsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    DataModule::class,
    NetworkModule::class,
    ApiModule::class
])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: ListBuildingsFragment)

}
