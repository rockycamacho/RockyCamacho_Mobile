package com.rockycamacho.willow.testapp.presentation

import android.os.Bundle
import com.rockycamacho.willow.testapp.R
import com.rockycamacho.willow.testapp.di.AppComponent
import com.rockycamacho.willow.testapp.presentation.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun injectDependencies(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(
                    R.id.fragment_container,
                    ExercisesListFragment.newInstance()
                )
                .addToBackStack(ExercisesListFragment::class.java.simpleName)
                .commit()
        }
    }
}
