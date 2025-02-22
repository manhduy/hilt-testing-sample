package com.duyha.hilttestingsample.calulator

import androidx.lifecycle.ViewModel
import com.duyha.hilttestingsample.domain.SumUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val calculator: SumUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<CalculatorUiState>(CalculatorUiState.Initial)
    val uiState: StateFlow<CalculatorUiState> = _uiState.asStateFlow()

    fun onSumClick(textA: String, textB: String) {
        val intA = textA.toIntOrNull()
        if (intA == null) {
            _uiState.value = CalculatorUiState.InvalidTextA
            return
        }

        val intB = textB.toIntOrNull()
        if (intB == null) {
            _uiState.value = CalculatorUiState.InvalidTextB
            return
        }

        _uiState.value = CalculatorUiState.Success(calculator(intA, intB))
    }
}