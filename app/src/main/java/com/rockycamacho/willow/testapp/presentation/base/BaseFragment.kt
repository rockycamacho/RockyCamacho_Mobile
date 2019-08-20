package com.ezqueue.owner.app.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.rockycamacho.willow.testapp.WillowTestApp
import com.rockycamacho.willow.testapp.di.AppComponent
import com.rockycamacho.willow.testapp.presentation.ViewModelFactory
import javax.inject.Inject

abstract class BaseFragment<VM : ViewModel> : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<VM>
    lateinit var viewModel: VM

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val appComponent = WillowTestApp[context].getAppComponent()
        injectDependencies(appComponent)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModelClass())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutResId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        bindViewModel()
    }

    open fun initViews() {

    }

    abstract fun injectDependencies(appComponent: AppComponent)

    abstract fun getLayoutResId(): Int

    abstract fun bindViewModel()

    abstract fun getViewModelClass(): Class<VM>

}
