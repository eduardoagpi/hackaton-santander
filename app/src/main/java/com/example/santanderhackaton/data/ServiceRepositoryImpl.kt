package com.example.santanderhackaton.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.Date

const val DELAY_CALL_SERVICE_TIME_MILLIS = 5000L
class ServiceRepositoryImpl : ServiceRepository {

    private val apiService: ApiService

    private val flows = mutableMapOf<ServiceEnum, Flow<ServiceStatus>>()

    private var shouldEmit = false

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://hp-api.onrender.com")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)
    }

    override fun getServiceStatusFlow(service: ServiceEnum): Flow<ServiceStatus> {
        val flow = flows[service]
        shouldEmit = true
        return if(flow == null) {
            val newFlow = createServiceFlow(service)
            flows[service] = newFlow
            newFlow
        } else {
            flow
        }
    }

    private fun createServiceFlow(serviceEnum: ServiceEnum): Flow<ServiceStatus> = flow {
        while(shouldEmit) {
            var responseBody = ""
            var httpCode: Int
            try {
                val response = serviceEnum.callService().invoke()
                httpCode = response.code()
                responseBody = response.body() ?: ""
            }catch (e: Exception) {
                e.printStackTrace()
                httpCode = 500
            }

            val serviceStatus = ServiceStatus(serviceEnum, serviceEnum.getName(),serviceEnum.getRoute(),httpCode, Date(), responseBody)
            emit(serviceStatus)
            delay(DELAY_CALL_SERVICE_TIME_MILLIS)
        }
    }
    override fun stopListening() {
        shouldEmit = false
    }

    private fun ServiceEnum.callService(): suspend ()-> Response<String> {
        return when(this) {
            ServiceEnum.Characters -> apiService::getCharacters
            ServiceEnum.CharactersByID -> apiService::getCharacters
            ServiceEnum.Students -> apiService::getCharacters
            ServiceEnum.Staff -> apiService::getCharacters
            ServiceEnum.Spells -> apiService::getCharacters
            ServiceEnum.CharactersInHouse ->apiService::getCharacters
        }
    }

}

