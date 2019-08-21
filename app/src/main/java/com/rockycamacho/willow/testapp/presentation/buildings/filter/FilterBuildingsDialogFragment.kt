package com.rockycamacho.willow.testapp.presentation.buildings.filter

import android.os.Bundle
import android.view.View
import com.google.android.material.button.MaterialButton
import com.google.android.material.button.MaterialButtonToggleGroup
import com.jakewharton.rxbinding3.view.clicks
import com.rockycamacho.willow.testapp.R
import com.rockycamacho.willow.testapp.di.AppComponent
import com.rockycamacho.willow.testapp.presentation.base.BaseBottomSheetDialogFragment
import com.rockycamacho.willow.testapp.presentation.buildings.BuildingFilter
import kotlinx.android.synthetic.main.fragment_filter_buildings.*

class FilterBuildingsDialogFragment : BaseBottomSheetDialogFragment() {

    private lateinit var onSelectFilterListener: (BuildingFilter) -> Unit

    companion object {
        const val NAME = "FilterBuildingsDialogFragment"
        const val EXTRA_FILTER = "filter"

        fun newInstance(filter: BuildingFilter): FilterBuildingsDialogFragment {
            val fragment = FilterBuildingsDialogFragment()
            fragment.arguments = Bundle().apply {
                putParcelable(EXTRA_FILTER, filter)
            }
            return fragment
        }
    }

    override fun injectDependencies(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    override fun getLayoutResId(): Int = R.layout.fragment_filter_buildings

    override fun initViews(savedInstanceState: Bundle?) {
        val countryMap = mapOf(
            Pair("Australia", country_australia),
            Pair("Germany", country_germany)
        )
        val cityMap = mapOf(
            Pair("Melbourne", city_melbourne),
            Pair("Sydney", city_sydney),
            Pair("Rottweil", city_rottweil)
        )
        select_country.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (checkedId == R.id.country_australia) {
                city_melbourne.isChecked = isChecked
                city_sydney.isChecked = isChecked
                select_australian_city.visibility = when (isChecked) {
                    true -> View.VISIBLE
                    else -> View.GONE
                }
            }
            if (checkedId == R.id.country_germany) {
                city_rottweil.isChecked = isChecked
                select_german_city.visibility = when (isChecked) {
                    true -> View.VISIBLE
                    else -> View.GONE
                }
            }
        }
        clear_countries_button.clicks()
            .subscribe {
                select_country.clearChecked()
            }
        clear_cities_button.clicks()
            .subscribe {
                for (cityGroups in listOf<MaterialButtonToggleGroup>(select_australian_city, select_german_city)) {
                    cityGroups.clearChecked()
                }
            }
        save_filters_button.clicks()
            .subscribe {
                saveFilters(countryMap, cityMap)
            }

        val filter = arguments!!.getParcelable<BuildingFilter>(EXTRA_FILTER)
        for((country, view) in countryMap) {
            view.isChecked = filter.countries.contains(country)
        }
        select_australian_city.visibility = when (country_australia.isChecked) {
            true -> View.VISIBLE
            else -> View.GONE
        }
        select_german_city.visibility = when (country_germany.isChecked) {
            true -> View.VISIBLE
            else -> View.GONE
        }
        for((city, view) in cityMap) {
            view.isChecked = filter.cities.contains(city)
        }
    }

    private fun saveFilters(
        countryMap: Map<String, MaterialButton>,
        cityMap: Map<String, MaterialButton>
    ) {
        val updatedFilter = BuildingFilter()
        for((country, view) in countryMap) {
            if(view.isChecked) {
                updatedFilter.countries.add(country)
            }
        }
        for((city, view) in cityMap) {
            if(view.isChecked) {
                updatedFilter.cities.add(city)
            }
        }
        onSelectFilterListener?.invoke(updatedFilter)

        dismiss()
    }

    fun setOnSelectFilterListener(onSelectFilterListener: (BuildingFilter) -> Unit) {
        this.onSelectFilterListener = onSelectFilterListener
    }
}