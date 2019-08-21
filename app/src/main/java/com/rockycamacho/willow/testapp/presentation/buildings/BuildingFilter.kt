package com.rockycamacho.willow.testapp.presentation.buildings

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BuildingFilter(
    var countries: MutableList<String> = mutableListOf(),
    var cities: MutableList<String> = mutableListOf()
) : Parcelable