package com.example.santanderhackaton.di

import com.example.santanderhackaton.data.ServiceRepository
import com.example.santanderhackaton.data.ServiceRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindHEndpointStatusService(
        serviceRepositoryImpl: ServiceRepositoryImpl
    ): ServiceRepository
}