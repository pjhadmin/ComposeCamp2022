package com.example.compose.rally

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasParent
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import com.example.compose.rally.ui.components.RallyTopAppBar
import org.junit.Rule
import org.junit.Test

class TopBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun rallyTopAppBarTest_currentLabelExists() {
        val allScreens = RallyScreen.values().toList()

        composeTestRule.setContent {
            RallyTopAppBar(
                allScreens = allScreens,
                onTabSelected = { },
                currentScreen = RallyScreen.Accounts
            )
        }

        composeTestRule
            .onNode(
                hasText(RallyScreen.Accounts.name.uppercase()) and
                hasParent(
                    hasContentDescription(RallyScreen.Accounts.name)
                ),
                useUnmergedTree = true
            ).assertExists()
    }


    @Test
    fun rallyTopAppBarTest() {
        val allScreens = RallyScreen.values().toList()

        composeTestRule.setContent {
            RallyTopAppBar(
                allScreens = allScreens,
                onTabSelected = { },
                currentScreen = RallyScreen.Accounts
            )
        }

        composeTestRule.onRoot().printToLog("currentLabelExists")

        composeTestRule
            .onNodeWithContentDescription(RallyScreen.Accounts.name)
            .assertExists()
    }

    @Test
    fun rallyTopAppBarTest_changedOtherTab() {
        val allScreens = RallyScreen.values().toList()
        var selectedState by mutableStateOf(RallyScreen.Accounts)

        composeTestRule.setContent {
            RallyTopAppBar(
                allScreens = allScreens,
                onTabSelected = { },
                currentScreen = selectedState
            )
        }

        composeTestRule
            .onNodeWithContentDescription(RallyScreen.Accounts.name, useUnmergedTree = true)
            .assertIsDisplayed()
            .assertIsSelected()

        selectedState = RallyScreen.Bills
        composeTestRule
            .onNodeWithContentDescription(RallyScreen.Bills.name, useUnmergedTree = true)
            .assertIsDisplayed()
            .assertIsSelected()
    }
}