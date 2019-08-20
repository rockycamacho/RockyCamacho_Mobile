package com.rockycamacho.willow.testapp.presentation.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rockycamacho.willow.testapp.WillowTestApp
import com.rockycamacho.willow.testapp.di.AppComponent

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val appComponent = WillowTestApp[this].getAppComponent()
        injectDependencies(appComponent)
        super.onCreate(savedInstanceState)
    }

    abstract fun injectDependencies(appComponent: AppComponent)

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        for (fragment in supportFragmentManager.fragments) {
            fragment.onActivityResult(requestCode, resultCode, data)
        }
    }

}