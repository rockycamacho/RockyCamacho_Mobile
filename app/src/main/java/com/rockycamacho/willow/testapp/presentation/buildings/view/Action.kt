package com.rockycamacho.willow.testapp.presentation.buildings.view

import com.rockycamacho.willow.testapp.data.network.models.Building
import com.ww.roxie.BaseAction

sealed class Action : BaseAction {

    data class FetchBuilding(val building: Building) : Action()

}
