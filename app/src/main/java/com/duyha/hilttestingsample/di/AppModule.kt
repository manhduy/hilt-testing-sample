package com.duyha.hilttestingsample.di

import com.duyha.hilttestingsample.Calculator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideCalculator(): Calculator = Calculator()
}