package com.rockycamacho.willow.testapp.robots

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.rockycamacho.willow.testapp.R
import org.hamcrest.core.IsNot.not

class SelectBuildingFilterRobot : BaseTestRobot() {

    fun isShown() {
        onView(withId(R.id.country_australia)).check(matches(isDisplayed()))
        onView(withId(R.id.country_germany)).check(matches(isDisplayed()))

        onView(withId(R.id.save_filters_button)).check(matches(isDisplayed()))
    }

    fun clickSaveFilter() {
        clickButton(R.id.save_filters_button)
    }

    fun goToListBuildingsScreen(func: ListBuildingsRobot.() -> Unit) = ListBuildingsRobot()
        .apply { func() }

    fun pressBackButton() {
        pressBack()
    }

    fun clickButtonWithText(text: String) {
        onView(withText(text)).perform(click())
    }

    fun assertButtonNotVisible(text: String) {
        onView(withText(text)).check(matches(not(isDisplayed())))
    }

    fun assertButtonVisible(text: String) {
        onView(withText(text)).check(matches(isDisplayed()))
    }

    fun assertButtonChecked(text: String) {
        onView(withText(text)).check(matches(isChecked()))
    }

    fun assertButtonNotChecked(text: String) {
        onView(withText(text)).check(matches(isNotChecked()))
    }
}

fun selectBuildingFilter(func: SelectBuildingFilterRobot.() -> Unit) = SelectBuildingFilterRobot()
    .apply { func() }