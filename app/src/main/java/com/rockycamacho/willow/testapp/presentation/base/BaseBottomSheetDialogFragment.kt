package com.rockycamacho.willow.testapp.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.rockycamacho.willow.testapp.WillowTestApp
import com.rockycamacho.willow.testapp.di.AppComponent

abstract class BaseBottomSheetDialogFragment : BottomSheetDialogFragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val appComponent = WillowTestApp[context].getAppComponent()
        injectDependencies(appComponent)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutResId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews(savedInstanceState)
    }

    open fun initViews(savedInstanceState: Bundle?) {

    }

    abstract fun injectDependencies(appComponent: AppComponent)

    abstract fun getLayoutResId(): Int

}
