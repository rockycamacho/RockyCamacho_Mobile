package com.rockycamacho.willow.testapp.presentation.buildings.list

import android.os.Parcelable
import com.rockycamacho.willow.testapp.data.network.models.Building
import com.rockycamacho.willow.testapp.presentation.buildings.BuildingFilter
import com.ww.roxie.BaseState
import kotlinx.android.parcel.Parcelize

@Parcelize
data class State(
    val isLoading: Boolean = false,
    val data: List<Building> = listOf(),
    val filteredData: List<Building> = listOf(),
    val filter: BuildingFilter? = null,
    val exception: Throwable? = null
) : BaseState, Parcelable