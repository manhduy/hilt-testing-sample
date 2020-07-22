package com.duyha.hilttestingsample.di

import com.duyha.hilttestingsample.Calculator
import com.duyha.hilttestingsample.calulator.CalculatorViewModelFactory
import com.duyha.hilttestingsample.calulator.CalculatorViewModelFactoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {

    @Provides
    fun provideCalculatorViewModelFactory(calculator: Calculator): CalculatorViewModelFactory =
        CalculatorViewModelFactoryImpl(calculator)
}