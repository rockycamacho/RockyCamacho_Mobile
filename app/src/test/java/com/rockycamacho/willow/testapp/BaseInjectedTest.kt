package com.rockycamacho.willow.testapp

import com.github.javafaker.Faker
import com.rockycamacho.willow.testapp.di.TestComponent
import org.junit.Rule
import javax.inject.Inject

abstract class BaseInjectedTest : BaseTest() {

    @Rule
    @JvmField
    var daggerRule = InjectedTestDaggerRule(this)

    @Inject
    protected lateinit var faker: Faker

    abstract fun injectDependencies(testComponent: TestComponent)
}