package com.rockycamacho.willow.testapp.data.network

import com.rockycamacho.willow.testapp.BaseInjectedTest
import com.rockycamacho.willow.testapp.di.TestComponent
import org.junit.Test
import javax.inject.Inject

class GetUserApiTest : BaseInjectedTest() {
    @Inject
    lateinit var apiService: ApiService

    override fun injectDependencies(testComponent: TestComponent) {
        testComponent.inject(this)
    }

    @Test
    fun `get user success`() {
        apiService.getUser()
            .test()
            .assertComplete()
            .assertNoErrors()
    }
}