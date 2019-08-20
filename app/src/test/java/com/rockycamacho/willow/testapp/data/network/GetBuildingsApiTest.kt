package com.rockycamacho.willow.testapp.data.network

import com.rockycamacho.willow.testapp.BaseInjectedTest
import com.rockycamacho.willow.testapp.di.TestComponent
import org.junit.Test
import javax.inject.Inject

class GetBuildingsApiTest: BaseInjectedTest() {
    @Inject
    lateinit var apiService: ApiService

    override fun injectDependencies(testComponent: TestComponent) {
        testComponent.inject(this)
    }

    @Test
    fun `get buildings success`() {
        apiService.getBuildings()
            .test()
            .assertComplete()
            .assertNoErrors()
    }
}