package com.rockycamacho.willow.testapp.presentation.buildings.view

import android.os.Parcelable
import com.rockycamacho.willow.testapp.data.network.models.Building
import com.ww.roxie.BaseState
import kotlinx.android.parcel.Parcelize

@Parcelize
data class State(
    val isLoading: Boolean = false,
    val data: Building? = null,
    val exception: Throwable? = null
) : BaseState, Parcelable