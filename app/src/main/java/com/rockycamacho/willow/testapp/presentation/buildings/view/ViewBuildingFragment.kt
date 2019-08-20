package com.rockycamacho.willow.testapp.presentation.buildings.view

import android.os.Bundle
import com.rockycamacho.willow.testapp.presentation.base.BaseFragment
import com.rockycamacho.willow.testapp.data.network.models.Building
import com.rockycamacho.willow.testapp.di.AppComponent
import com.rockycamacho.willow.testapp.presentation.buildings.list.ListBuildingsFragment

class ViewBuildingFragment : BaseFragment<ViewBuildingViewModel>() {

    companion object {
        fun newInstance(building: Building): ListBuildingsFragment {
            val fragment = ListBuildingsFragment()
            fragment.arguments = Bundle().apply {
                putParcelable("building", building)
            }
            return fragment
        }
    }

    override fun injectDependencies(appComponent: AppComponent) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLayoutResId(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getViewModelClass(): Class<ViewBuildingViewModel> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
