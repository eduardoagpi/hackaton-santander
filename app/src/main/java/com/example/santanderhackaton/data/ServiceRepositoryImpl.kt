package com.example.santanderhackaton.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.util.Date
import javax.inject.Inject

const val DELAY_CALL_SERVICE_TIME_MILLIS = 5000L
class ServiceRepositoryImpl @Inject constructor(private val apiService: ApiService) : ServiceRepository {

    private val flows = mutableMapOf<ServiceEnum, Flow<ServiceStatus>>()

    private var shouldEmit = false

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
                val simulateFailure = System.currentTimeMillis() % 3L == 0L
                if(simulateFailure) throw Exception("Failure Simulated")
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
            ServiceEnum.CharactersByID -> {{apiService.getCharacterById("9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8") } }
            ServiceEnum.Students -> apiService::getStudents
            ServiceEnum.Staff -> apiService::getStaff
            ServiceEnum.Spells -> apiService::getSpells
            ServiceEnum.CharactersInHouse ->  { { apiService.getCharactersInHouse("Slytherin") }
            }
        }
    }

}

