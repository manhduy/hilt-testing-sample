package com.duyha.hilttestingsample.calulator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.duyha.hilttestingsample.Calculator
import javax.inject.Inject


interface CalculatorViewModelFactory : ViewModelProvider.Factory

@Suppress("UNCHECKED_CAST")
class CalculatorViewModelFactoryImpl (
    private val calculator: Calculator
) : CalculatorViewModelFactory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CalculatorViewModel(calculator) as T
    }
}