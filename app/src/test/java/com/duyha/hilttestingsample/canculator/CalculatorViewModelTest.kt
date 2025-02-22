package com.duyha.hilttestingsample.canculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.duyha.hilttestingsample.domain.SumUseCase
import com.duyha.hilttestingsample.calulator.CalculatorUiState
import com.duyha.hilttestingsample.calulator.CalculatorViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CalculatorViewModelTest {

    private lateinit var sumUseCase: SumUseCase
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
        sumUseCase = mockk()
        viewModel = CalculatorViewModel(sumUseCase)
    }

    @Test
    fun test_SumReturnFromUseCase_UpdateUiState() = runTest {
        //Given
        every { sumUseCase.invoke(a, b) } returns  sum
        //When
        viewModel.onSumClick(textA, textB)
        //Then
        verify { sumUseCase.invoke(a, b) }
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
        verify(exactly = 0) { sumUseCase.invoke(any(), any()) }
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
        verify(exactly = 0) { sumUseCase.invoke(any(), any()) }
        viewModel.uiState.test {
            assertEquals(CalculatorUiState.InvalidTextB, awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }
}