package com.example.santanderhackaton.di

import com.example.santanderhackaton.data.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

const val API_URL = "https://hp-api.onrender.com"

@Module
@InstallIn(ActivityComponent::class)
object RetrofiteModule {

    @Provides
    fun provideApiService(): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
       return retrofit.create(ApiService::class.java)
    }
}