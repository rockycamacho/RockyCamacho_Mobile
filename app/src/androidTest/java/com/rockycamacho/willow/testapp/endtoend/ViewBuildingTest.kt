package com.rockycamacho.willow.testapp.endtoend

import com.rockycamacho.willow.testapp.BaseEspressoTest
import com.rockycamacho.willow.testapp.di.EspressoAppComponent
import com.rockycamacho.willow.testapp.robots.listBuildings
import org.junit.Test

class ViewBuildingTest : BaseEspressoTest() {
    override fun injectDependencies(testComponent: EspressoAppComponent) {
        testComponent.inject(this)
    }

    @Test
    fun clickingItemInListBuildingsScreen_GoToViewBuildingScreen() {
        activityTestRule.launchActivity(null)
        listBuildings {
            isShown()
            clickItem(0)
        }.goToBuildingScreen {
            isShown()
        }
    }

    @Test
    fun pressingBackFromViewBuildingScreen_GoBackToListBuildingsScreen() {
        activityTestRule.launchActivity(null)
        listBuildings {
            isShown()
            clickItem(0)
        }.goToBuildingScreen {
            isShown()
            pressBackButton()
        }.goToListBuildingsScreen {
            isShown()
        }
    }


}