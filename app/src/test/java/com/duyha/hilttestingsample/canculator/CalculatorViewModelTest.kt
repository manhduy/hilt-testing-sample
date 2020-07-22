package com.duyha.hilttestingsample.canculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.duyha.hilttestingsample.Calculator
import com.duyha.hilttestingsample.Event
import com.duyha.hilttestingsample.R
import com.duyha.hilttestingsample.calulator.CalculatorViewModel
import com.duyha.hilttestingsample.getOrAwaitValue
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CalculatorViewModelTest {

    private lateinit var calculator: Calculator
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
        calculator = mockk<Calculator>()
        viewModel = CalculatorViewModel(calculator)
    }

    @Test
    fun test_SumReturnFromCalculator_LiveDataChanged() {
        //Given
        every { calculator.sum(a, b) } returns sum
        //When
        viewModel.onSumClick(textA, textB)
        //Then
        verify { calculator.sum(a, b) }
        assertEquals(sum, viewModel.sum.getOrAwaitValue())
    }

    @Test
    fun test_EmptyTextA_ShowMessage() {
        //Given
        every { calculator.sum(a, b) } returns sum
        //When
        viewModel.onSumClick("", textB)
        //Then
        assertEquals(0, viewModel.sum.getOrAwaitValue())
        assertEquals(R.string.msg_input_a, viewModel.msg.getOrAwaitValue().peekContent())
    }

    @Test
    fun test_EmptyTextB_ShowMessage() {
        //Given
        every { calculator.sum(a, b) } returns sum
        //When
        viewModel.onSumClick(textA, "")
        //Then
        assertEquals(0, viewModel.sum.getOrAwaitValue())
        assertEquals(R.string.msg_input_b, viewModel.msg.getOrAwaitValue().peekContent())
    }
}