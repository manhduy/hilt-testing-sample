package com.duyha.hilttestingsample.di

import com.duyha.hilttestingsample.Calculator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideCalculator(): Calculator = Calculator()
}