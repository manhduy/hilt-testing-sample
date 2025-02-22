package com.duyha.hilttestingsample.di

import com.duyha.hilttestingsample.domain.SumUseCase
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
    fun provideCalculator(): SumUseCase = SumUseCase()
}