package com.duyha.hilttestingsample.calculator

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.duyha.hilttestingsample.calulator.CalculatorScreen
import com.duyha.hilttestingsample.calulator.CalculatorUiState
import com.duyha.hilttestingsample.calulator.CalculatorViewModel
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumentation test for [CalculatorScreen]
 */
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class CalculatorScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @BindValue
    @JvmField
    val viewModel: CalculatorViewModel = mockk(relaxed = true)

    @Test
    fun test_SuccessState_ShowSumResult() {
        //Given
        val uiState: MutableStateFlow<CalculatorUiState> = MutableStateFlow(CalculatorUiState.Initial)
        every { viewModel.uiState } returns uiState
        composeTestRule.setContent {
            CalculatorScreen(viewModel)
        }
        //When
        uiState.value = CalculatorUiState.Success(10)
        //Then
        composeTestRule.onNodeWithText("10").assertIsDisplayed()
    }

    @Test
    fun test_InvalidTextAState_ShowErrorMessage() {
        //Given
        val uiState: MutableStateFlow<CalculatorUiState> = MutableStateFlow(CalculatorUiState.Initial)
        every { viewModel.uiState } returns uiState
        composeTestRule.setContent {
            CalculatorScreen(viewModel)
        }
        //When
        uiState.value = CalculatorUiState.InvalidTextA
        //Then
        composeTestRule.onNodeWithText("Invalid A").assertIsDisplayed()
    }

    @Test
    fun test_InvalidTextBState_ShowErrorMessage() {
        //Given
        val uiState: MutableStateFlow<CalculatorUiState> = MutableStateFlow(CalculatorUiState.Initial)
        every { viewModel.uiState } returns uiState
        composeTestRule.setContent {
            CalculatorScreen(viewModel)
        }
        //When
        uiState.value = CalculatorUiState.InvalidTextB
        //Then
        composeTestRule.onNodeWithText("Invalid B").assertIsDisplayed()
    }
}