package com.rockycamacho.willow.testapp

import com.rockycamacho.willow.testapp.di.TestModule
import com.rockycamacho.willow.testapp.di.ApiModule
import com.rockycamacho.willow.testapp.di.DataModule
import com.rockycamacho.willow.testapp.di.NetworkModule
import com.rockycamacho.willow.testapp.di.TestComponent
import it.cosenonjaviste.daggermock.DaggerMockRule

class InjectedTestDaggerRule(test: BaseInjectedTest) : DaggerMockRule<TestComponent>(
    TestComponent::class.java,
    TestModule(),
    DataModule(),
    NetworkModule(),
    ApiModule(BuildConfig.API_ENDPOINT)
) {

    init {
        set { component ->
            test.injectDependencies(component)
        }
    }
}
