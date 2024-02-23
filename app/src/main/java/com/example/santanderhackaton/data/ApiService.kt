package com.example.santanderhackaton.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET(CHARACTERS_ROUTE)
    suspend  fun getCharacters(): Response<String>

    @GET(CHARACTERS_BY_ID_ROUTE)
    suspend  fun getCharacterById(@Path("id") characterId: String): Response<String>

    @GET(STUDENTS_ROUTE)
    suspend  fun getStudents(): Response<String>

    @GET(STAFF_ROUTE)
    suspend  fun getStaff(): Response<String>

    @GET(SPELLS_ROUTE)
    suspend  fun getSpells(): Response<String>

    @GET(CHARACTERS_IN_HOUSE_ROUTE)
    suspend  fun getCharactersInHouse(@Path("house") houseId: String): Response<String>
}
