package com.duyha.hilttestingsample.calulator

/**
 * UiState of the Calculator screen
 */
sealed interface CalculatorUiState {
    data object Initial : CalculatorUiState
    data class Success(val sum: Int) : CalculatorUiState
    data object InvalidTextA : CalculatorUiState
    data object InvalidTextB : CalculatorUiState
}