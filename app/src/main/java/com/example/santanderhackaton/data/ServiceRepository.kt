package com.example.santanderhackaton.data

import kotlinx.coroutines.flow.Flow

interface ServiceRepository {
    fun getServiceStatusFlow(service: ServiceEnum) : Flow<ServiceStatus>

    fun stopListening()
}