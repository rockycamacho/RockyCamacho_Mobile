package com.rockycamacho.willow.testapp.robots

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import com.rockycamacho.willow.testapp.R

class ViewBuildingRobot : BaseTestRobot() {

    fun isShown() {
        onView(withId(R.id.image)).check(matches(isDisplayed()))
        onView(withId(R.id.client_name)).check(matches(isDisplayed()))
        onView(withId(R.id.name)).check(matches(isDisplayed()))
        onView(withId(R.id.address)).check(matches(isDisplayed()))
        onView(withId(R.id.view_on_map_button)).check(matches(isDisplayed()))
    }

    fun pressBackButton() {
        pressBack()
    }

    fun goToListBuildingsScreen(func: ListBuildingsRobot.() -> Unit) = ListBuildingsRobot()
        .apply { func() }

}

fun viewBuilding(func: ViewBuildingRobot.() -> Unit) = ViewBuildingRobot()
    .apply { func() }