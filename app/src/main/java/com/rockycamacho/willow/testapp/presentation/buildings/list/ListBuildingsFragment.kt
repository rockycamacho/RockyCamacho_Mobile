package com.rockycamacho.willow.testapp.presentation.buildings.list

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuBuilder
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.rockycamacho.willow.testapp.presentation.base.BaseFragment
import com.jakewharton.rxbinding3.swiperefreshlayout.refreshes
import com.rockycamacho.willow.testapp.R
import com.rockycamacho.willow.testapp.data.network.models.AvailableProduct
import com.rockycamacho.willow.testapp.data.network.models.Building
import com.rockycamacho.willow.testapp.di.AppComponent
import com.rockycamacho.willow.testapp.presentation.buildings.BuildingFilter
import com.rockycamacho.willow.testapp.presentation.buildings.filter.FilterBuildingsDialogFragment
import com.rockycamacho.willow.testapp.presentation.buildings.view.ViewBuildingFragment
import kotlinx.android.synthetic.main.fragment_list_buildings.*

class ListBuildingsFragment : BaseFragment<ListBuildingsViewModel>() {

    private lateinit var adapter: BuildingListAdapter

    companion object {
        fun newInstance() = ListBuildingsFragment()
    }

    override fun injectDependencies(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    override fun getLayoutResId(): Int = R.layout.fragment_list_buildings

    override fun getViewModelClass(): Class<ListBuildingsViewModel> = ListBuildingsViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_buildings, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_filter) {
            val defaultFilter =
                BuildingFilter(mutableListOf("Australia", "Germany"), mutableListOf("Melbourne", "Sydney", "Rottweil"))
            val filter = viewModel.observableState.value?.filter ?: defaultFilter
            val dialog = FilterBuildingsDialogFragment.newInstance(filter)
            dialog.setOnSelectFilterListener { filter ->
                viewModel.dispatch(Action.ChangeFilter(filter))
            }
            dialog.show(requireFragmentManager(), FilterBuildingsDialogFragment.NAME)
            return true
        }
        return false
    }

    override fun initViews(savedInstanceState: Bundle?) {
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        refresh.isEnabled = true
        refresh.refreshes()
            .subscribe {
                viewModel.dispatch(Action.FetchBuildings)
            }
        list.layoutManager = GridLayoutManager(requireContext(), 1)
        list.setHasFixedSize(true)
        adapter = BuildingListAdapter({ building ->
            goToMap(building)
        }, { building ->
            adapter.notifyDataSetChanged()
        }, { building ->
            if (!building.availableProducts.contains(AvailableProduct.EXPLORER)) {
                Toast.makeText(requireActivity(), getString(R.string.error_explorer_not_available), Toast.LENGTH_SHORT)
                    .show()
            } else {
                requireFragmentManager().beginTransaction()
                    .replace(
                        R.id.fragment_container,
                        ViewBuildingFragment.newInstance(building)
                    )
                    .addToBackStack(
                        ViewBuildingFragment::class.java.simpleName
                    )
                    .commit()
            }
        })
        list.adapter = adapter

        viewModel.observableState.observe(this, Observer { state ->
            state?.let { renderState(state) }
        })

        if (savedInstanceState == null) {
            viewModel.dispatch(Action.FetchBuildings)
        }
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
        refresh.isRefreshing = state.isLoading
        adapter.submitList(state.filteredData)
        empty.visibility = when (state.data.isEmpty()) {
            true -> View.VISIBLE
            else -> View.GONE
        }
        list.visibility = when (state.data.isNotEmpty()) {
            true -> View.VISIBLE
            else -> View.GONE
        }
        state.exception?.let {
            Toast.makeText(requireActivity(), it.message, Toast.LENGTH_SHORT).show()
        }
    }
}