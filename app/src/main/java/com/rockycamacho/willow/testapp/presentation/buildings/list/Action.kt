package com.rockycamacho.willow.testapp.presentation.buildings.list

import com.rockycamacho.willow.testapp.presentation.buildings.BuildingFilter
import com.ww.roxie.BaseAction

sealed class Action : BaseAction {

    object FetchBuildings : Action()
    data class ChangeFilter(var filters: BuildingFilter? = null) : Action()

}
