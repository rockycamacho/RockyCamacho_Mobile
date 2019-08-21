package com.rockycamacho.willow.testapp.presentation.buildings.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import coil.api.load
import coil.transform.RoundedCornersTransformation
import com.jakewharton.rxbinding3.view.clicks
import com.rockycamacho.willow.testapp.R
import com.rockycamacho.willow.testapp.data.network.models.Building
import com.rockycamacho.willow.testapp.di.AppComponent
import com.rockycamacho.willow.testapp.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_view_building.*
import kotlinx.android.synthetic.main.item_building.view.*
import timber.log.Timber
import java.lang.StringBuilder

class ViewBuildingFragment : BaseFragment<ViewBuildingViewModel>() {

    companion object {
        const val EXTRA_BUILDING = "building"

        fun newInstance(building: Building): ViewBuildingFragment {
            val fragment = ViewBuildingFragment()
            fragment.arguments = Bundle().apply {
                putParcelable(EXTRA_BUILDING, building)
            }
            return fragment
        }
    }

    override fun injectDependencies(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    override fun getLayoutResId(): Int = R.layout.fragment_view_building

    override fun getViewModelClass(): Class<ViewBuildingViewModel> = ViewBuildingViewModel::class.java

    override fun initViews(savedInstanceState: Bundle?) {
        val building = arguments!!.getParcelable<Building>(EXTRA_BUILDING)
        Timber.d("building: %s", building)

        viewModel.observableState.observe(this, Observer { state ->
            state?.let { renderState(state) }
        })
        viewModel.dispatch(Action.FetchBuilding(building))
    }

    private fun goToMap(item: Building) {
        val uri = Uri.parse("geo:${item.address?.longitude},${item.address?.latitude}")
        val mapIntent = Intent(Intent.ACTION_VIEW, uri)
        if (mapIntent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(mapIntent)
        } else {
            Toast.makeText(requireActivity(), getString(R.string.error_no_map_app_install), Toast.LENGTH_SHORT).show()
        }
    }

    private fun renderState(state: State) {
        state.data?.let { building ->
            name.text = building.name
            client_name.text = building.clientName
            val addressLine = buildAddressLine(building)
            val stateCountry = buildStateCountry(building)
            address.text = "$addressLine $stateCountry"
            image.load(url = building.imageUrl) {
                crossfade(true)
                placeholder(R.color.placeholder_image)
            }
            view_on_map_button.clicks().subscribe {
                goToMap(building)
            }
        }
        state.exception?.let {
            Toast.makeText(requireActivity(), it.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun buildStateCountry(item: Building): StringBuilder {
        var stateCountry = StringBuilder()
        item.address?.let {
            it.state?.let {
                stateCountry.append(it)
            }
            it.country?.let {
                if (stateCountry.length > 0) {
                    stateCountry.append(", ")
                }
                stateCountry.append(it)
            }
        }
        return stateCountry
    }

    private fun buildAddressLine(item: Building): StringBuilder {
        var addressLine = StringBuilder()
        item.address?.let {
            it.line1?.let {
                addressLine.append(it)
            }
            it.line2?.let {
                if (addressLine.length > 0) {
                    addressLine.append(", ")
                }
                addressLine.append(it)
            }
        }
        return addressLine
    }

}
