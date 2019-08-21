package com.rockycamacho.willow.testapp

import android.os.AsyncTask
import androidx.test.platform.app.InstrumentationRegistry
import com.rockycamacho.willow.testapp.di.ApiModule
import com.rockycamacho.willow.testapp.di.DataModule
import com.rockycamacho.willow.testapp.di.EspressoAppComponent
import com.rockycamacho.willow.testapp.di.EspressoAppModule
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import it.cosenonjaviste.daggermock.DaggerMockRule

class InjectedEspressoTestDaggerRule(test: BaseEspressoTest) :
    DaggerMockRule<EspressoAppComponent>(
        EspressoAppComponent::class.java, EspressoAppModule(application),
        DataModule(),
        ApiModule(BuildConfig.API_ENDPOINT)
    ) {

    init {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR) }
        set { component ->
            val application = application
            application.setAppComponent(component)
            test.injectDependencies(component)
        }
    }

    companion object {

        protected val application: WillowTestApp
            get() =
                InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as WillowTestApp
    }
}
