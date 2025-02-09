package com.duyha.hilttestingsample.canculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.duyha.hilttestingsample.domain.SumUseCase
import com.duyha.hilttestingsample.calulator.CalculatorUiState
import com.duyha.hilttestingsample.calulator.CalculatorViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.then
import org.mockito.kotlin.mock

class CalculatorViewModelTest {

    private lateinit var calculator: SumUseCase
    private lateinit var viewModel: CalculatorViewModel

    private val textA = "4"
    private val a = textA.toInt()
    private val textB = "6"
    private val b = textB.toInt()
    private val sum = a + b

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        calculator = mock()
        viewModel = CalculatorViewModel(calculator)
    }

    @Test
    fun test_SumReturnFromCalculator_LiveDataChanged() = runTest {
        //Given
        given( calculator.invoke(a, b)).willReturn(sum)
        //When
        viewModel.onSumClick(textA, textB)
        //Then
        then(calculator).should().invoke(a, b)
        viewModel.uiState.test {
            assertEquals(CalculatorUiState.Success(sum), awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun test_EmptyTextA_ShowMessage() = runTest {
        //Given
        //When
        viewModel.onSumClick("", textB)
        //Then
        then(calculator).shouldHaveNoInteractions()
        viewModel.uiState.test {
            assertEquals(CalculatorUiState.InvalidTextA, awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun test_EmptyTextB_ShowMessage() = runTest {
        //Given
        //When
        viewModel.onSumClick("1", "")
        //Then
        then(calculator).shouldHaveNoInteractions()
        viewModel.uiState.test {
            assertEquals(CalculatorUiState.InvalidTextB, awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }
}