package com.rockycamacho.willow.testapp.presentation.buildings

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BuildingFilter(
    var countries: List<String> = listOf(),
    var cities: List<String> = listOf()
) : Parcelable