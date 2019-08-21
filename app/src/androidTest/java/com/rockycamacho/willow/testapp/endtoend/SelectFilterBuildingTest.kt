package com.rockycamacho.willow.testapp.endtoend

import com.rockycamacho.willow.testapp.BaseEspressoTest
import com.rockycamacho.willow.testapp.di.EspressoAppComponent
import com.rockycamacho.willow.testapp.robots.listBuildings
import org.junit.Test

class SelectFilterBuildingTest : BaseEspressoTest() {
    override fun injectDependencies(testComponent: EspressoAppComponent) {
        testComponent.inject(this)
    }

    @Test
    fun clickFilterIcon_GoToFilterBuildingScreen() {
        activityTestRule.launchActivity(null)
        listBuildings {
            isShown()
            clickFilter()
        }.goToSelectBuildingFilterScreen {
            isShown()
        }
    }

    @Test
    fun uncheckAustralia_AustralianCitiesShouldDisappear() {
        activityTestRule.launchActivity(null)
        listBuildings {
            isShown()
            clickFilter()
        }.goToSelectBuildingFilterScreen {
            isShown()

            assertButtonVisible("Sydney")
            assertButtonVisible("Melbourne")

            clickButtonWithText("Australia")

            assertButtonNotVisible("Sydney")
            assertButtonNotVisible("Melbourne")
        }
    }

    @Test
    fun uncheckGermany_GermanCitiesShouldDisappear() {
        activityTestRule.launchActivity(null)
        listBuildings {
            isShown()
            clickFilter()
        }.goToSelectBuildingFilterScreen {
            isShown()

            assertButtonVisible("Rottweil")

            clickButtonWithText("Germany")
            assertButtonNotVisible("Rottweil")
        }
    }

    @Test
    fun recheckAustralia_AustralianCitiesShouldReappear() {
        activityTestRule.launchActivity(null)
        listBuildings {
            isShown()
            clickFilter()
        }.goToSelectBuildingFilterScreen {
            isShown()

            clickButtonWithText("Australia")

            assertButtonNotVisible("Sydney")
            assertButtonNotVisible("Melbourne")

            clickButtonWithText("Australia")

            assertButtonVisible("Sydney")
            assertButtonVisible("Melbourne")
        }
    }

    @Test
    fun recheckGermany_GermanCitiesShouldReappear() {
        activityTestRule.launchActivity(null)
        listBuildings {
            isShown()
            clickFilter()
        }.goToSelectBuildingFilterScreen {
            isShown()

            clickButtonWithText("Germany")
            assertButtonNotVisible("Rottweil")

            clickButtonWithText("Germany")
            assertButtonVisible("Rottweil")
        }
    }

    @Test
    fun saveFilter_worksProperly_countryIsUnchecked() {
        activityTestRule.launchActivity(null)
        listBuildings {
            isShown()
            clickFilter()
        }.goToSelectBuildingFilterScreen {
            isShown()

            assertButtonChecked("Germany")
            assertButtonChecked("Rottweil")

            clickButtonWithText("Germany")
            assertButtonNotChecked("Germany")
            assertButtonNotVisible("Rottweil")

            clickSaveFilter()
        }.goToListBuildingsScreen {
            isShown()
            clickFilter()
        }.goToSelectBuildingFilterScreen {
            isShown()

            assertButtonNotChecked("Germany")
            assertButtonNotVisible("Rottweil")
        }
    }

    @Test
    fun saveFilter_worksProperly_cityIsUnchecked() {
        activityTestRule.launchActivity(null)
        listBuildings {
            isShown()
            clickFilter()
        }.goToSelectBuildingFilterScreen {
            isShown()

            assertButtonChecked("Australia")
            assertButtonChecked("Melbourne")
            assertButtonChecked("Sydney")

            clickButtonWithText("Sydney")

            clickSaveFilter()
        }.goToListBuildingsScreen {
            isShown()
            clickFilter()
        }.goToSelectBuildingFilterScreen {
            isShown()

            assertButtonChecked("Australia")
            assertButtonChecked("Melbourne")
            assertButtonNotChecked("Sydney")
        }
    }



}