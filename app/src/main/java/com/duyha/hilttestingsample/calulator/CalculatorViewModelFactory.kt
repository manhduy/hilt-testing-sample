package com.duyha.hilttestingsample.calulator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class CalculatorViewModelFactory @Inject constructor(
    private val calculator: Calculator
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CalculatorViewModel(calculator) as T
    }
}