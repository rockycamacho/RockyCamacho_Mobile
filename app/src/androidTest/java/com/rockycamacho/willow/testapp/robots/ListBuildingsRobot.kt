package com.rockycamacho.willow.testapp.robots

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import com.rockycamacho.willow.testapp.R

class ListBuildingsRobot : BaseTestRobot() {

    fun isShown() {
        onView(withId(R.id.list)).check(matches(isDisplayed()))
    }

    fun clickItem(position: Int) {
        clickListItem(R.id.list, position)
    }

    fun clickFilter() {
        clickButton(R.id.menu_filter)
    }

    fun goToSelectBuildingFilterScreen(func: SelectBuildingFilterRobot.() -> Unit): SelectBuildingFilterRobot {
        return selectBuildingFilter(func)
    }

    fun goToBuildingScreen(func: ViewBuildingRobot.() -> Unit): ViewBuildingRobot {
        return viewBuilding(func)
    }

}

fun listBuildings(func: ListBuildingsRobot.() -> Unit) = ListBuildingsRobot()
    .apply { func() }