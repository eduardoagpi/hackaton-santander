package com.example.santanderhackaton.data

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/api/characters")
    suspend  fun getCharacters(): Response<String>

}