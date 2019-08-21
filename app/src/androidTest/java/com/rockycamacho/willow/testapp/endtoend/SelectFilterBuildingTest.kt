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
    fun uncheckAustralia_AustralianCitiesShouldAppear() {
        activityTestRule.launchActivity(null)
        listBuildings {
            isShown()
            clickFilter()
        }.goToSelectBuildingFilterScreen {
            isShown()

            assertButtonVisible("Australia")
            assertButtonNotChecked("Australia")

            assertButtonNotVisible("Sydney")
            assertButtonNotVisible("Melbourne")

            clickButtonWithText("Australia")

            assertButtonVisible("Australia")
            assertButtonChecked("Australia")
            assertButtonVisible("Sydney")
            assertButtonVisible("Melbourne")
        }
    }

    @Test
    fun uncheckGermany_GermanCitiesShouldAppear() {
        activityTestRule.launchActivity(null)
        listBuildings {
            isShown()
            clickFilter()
        }.goToSelectBuildingFilterScreen {
            isShown()

            assertButtonVisible("Germany")
            assertButtonNotChecked("Germany")

            assertButtonNotVisible("Rottweil")

            clickButtonWithText("Germany")

            assertButtonVisible("Germany")
            assertButtonChecked("Germany")
            assertButtonVisible("Rottweil")
        }
    }

    @Test
    fun recheckAustralia_AustralianCitiesShouldDisappear() {
        activityTestRule.launchActivity(null)
        listBuildings {
            isShown()
            clickFilter()
        }.goToSelectBuildingFilterScreen {
            isShown()

            clickButtonWithText("Australia")

            assertButtonVisible("Sydney")
            assertButtonVisible("Melbourne")

            clickButtonWithText("Australia")

            assertButtonNotVisible("Sydney")
            assertButtonNotVisible("Melbourne")
        }
    }

    @Test
    fun recheckGermany_GermanCitiesShouldDisappear() {
        activityTestRule.launchActivity(null)
        listBuildings {
            isShown()
            clickFilter()
        }.goToSelectBuildingFilterScreen {
            isShown()

            clickButtonWithText("Germany")
            assertButtonVisible("Rottweil")

            clickButtonWithText("Germany")
            assertButtonNotVisible("Rottweil")
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

            assertButtonNotChecked("Germany")
            assertButtonNotVisible("Rottweil")
            assertButtonNotChecked("Rottweil")

            clickButtonWithText("Germany")
            assertButtonChecked("Germany")
            assertButtonVisible("Rottweil")

            clickSaveFilter()
        }.goToListBuildingsScreen {
            isShown()
            clickFilter()
        }.goToSelectBuildingFilterScreen {
            isShown()

            assertButtonChecked("Germany")
            assertButtonVisible("Rottweil")
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

            assertButtonNotChecked("Australia")
            assertButtonNotVisible("Melbourne")
            assertButtonNotChecked("Melbourne")
            assertButtonNotVisible("Sydney")
            assertButtonNotChecked("Sydney")

            clickButtonWithText("Australia")

            clickSaveFilter()
        }.goToListBuildingsScreen {
            isShown()
            clickFilter()
        }.goToSelectBuildingFilterScreen {
            isShown()

            assertButtonChecked("Australia")
            assertButtonChecked("Melbourne")
            assertButtonChecked("Sydney")
        }
    }

    @Test
    fun clickClearCities_clearCities() {
        activityTestRule.launchActivity(null)
        listBuildings {
            isShown()
            clickFilter()
        }.goToSelectBuildingFilterScreen {
            isShown()

            assertButtonNotChecked("Australia")
            assertButtonNotVisible("Melbourne")
            assertButtonNotChecked("Melbourne")
            assertButtonNotVisible("Sydney")
            assertButtonNotChecked("Sydney")

            clickButtonWithText("Australia")

            assertButtonChecked("Melbourne")
            assertButtonChecked("Sydney")

            clickClearCities()

            assertButtonNotChecked("Melbourne")
            assertButtonNotChecked("Sydney")
        }
    }

    @Test
    fun clickClearCountries() {
        activityTestRule.launchActivity(null)
        listBuildings {
            isShown()
            clickFilter()
        }.goToSelectBuildingFilterScreen {
            isShown()

            assertButtonNotChecked("Australia")

            clickButtonWithText("Australia")

            assertButtonChecked("Australia")

            clickClearCountries()

            assertButtonNotChecked("Australia")



            clickButtonWithText("Australia")
            clickButtonWithText("Germany")

            assertButtonChecked("Australia")
            assertButtonChecked("Germany")

            clickClearCountries()

            assertButtonNotChecked("Australia")
            assertButtonNotChecked("Germany")
        }
    }

}