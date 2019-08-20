package com.rockycamacho.willow.testapp.presentation.buildings.list

import com.rockycamacho.willow.testapp.data.network.models.Building
import com.rockycamacho.willow.testapp.presentation.buildings.BuildingFilter

sealed class Change {

    object Loading : Change()
    data class FilterData(val filter: BuildingFilter) : Change()
    data class Error(val exception: Throwable) : Change()
    data class Success(val data: List<Building>) : Change()

}
