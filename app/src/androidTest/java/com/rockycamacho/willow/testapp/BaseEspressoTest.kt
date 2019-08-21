package com.rockycamacho.willow.testapp

import androidx.test.rule.ActivityTestRule
import com.github.javafaker.Faker
import com.rockycamacho.willow.testapp.di.EspressoAppComponent
import com.rockycamacho.willow.testapp.presentation.MainActivity
import org.junit.Rule
import javax.inject.Inject

abstract class BaseEspressoTest : BaseTest() {

    @Rule
    @JvmField
    var daggerRule = InjectedEspressoTestDaggerRule(this)

    @Inject
    protected lateinit var faker: Faker

    abstract fun injectDependencies(testComponent: EspressoAppComponent)

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(MainActivity::class.java, false, false)


}