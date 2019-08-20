package com.rockycamacho.willow.testapp

import org.junit.After
import org.junit.Before
import org.junit.BeforeClass
import timber.log.Timber

abstract class BaseTest {

    companion object {
        @BeforeClass
        @Throws(Exception::class)
        @JvmStatic
        fun preventTestsOnProduction() {
            if (BuildConfig.BUILD_TYPE.equals("release")) {
                throw IllegalStateException("Should not test on production environment")
            }
        }
    }

    @Before
    @Throws(Exception::class)
    fun setUpLogging() {
        Timber.plant(TestTree())
    }

    @After
    @Throws(Exception::class)
    fun tearDownLogging() {
        Timber.uprootAll()
    }

}
