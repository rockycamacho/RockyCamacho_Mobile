package com.rockycamacho.willow.testapp.presentation.buildings.view

import com.rockycamacho.willow.testapp.data.network.models.Building

sealed class Change {

    object Loading : Change()
    data class Error(val exception: Throwable) : Change()
    data class Success(val data: Building) : Change()

}
