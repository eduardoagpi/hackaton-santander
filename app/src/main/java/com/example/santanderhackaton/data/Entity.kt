package com.example.santanderhackaton.data

import java.util.Date

enum class ServiceEnum {
    Characters, CharactersByID, Students, Staff, Spells, CharactersInHouse
}

data class ServiceStatus(
    val id: ServiceEnum,
    val name: String,
    val route: String,
    val httpCode: Int,
    val lastUpdate: Date,
    val response: String,
)

fun ServiceEnum.getRoute(): String {
    return when(this) {
        ServiceEnum.Characters -> "/api/characters"
        ServiceEnum.CharactersByID -> "https://hp-api.onrender.com/api/character/" //requires param
        ServiceEnum.Students -> "/api/characters/students"
        ServiceEnum.Staff -> "/api/characters/staff"
        ServiceEnum.Spells -> "/api/spells"
        ServiceEnum.CharactersInHouse -> "/api/characters/house" ////requires param
    }
}

fun ServiceEnum.getName(): String {
    return when(this) {
        ServiceEnum.Characters -> "Characters"
        ServiceEnum.CharactersByID -> "Characters By Id"
        ServiceEnum.Students -> "Students"
        ServiceEnum.Staff -> "Howarts Staff"
        ServiceEnum.Spells -> "Spells Catalog"
        ServiceEnum.CharactersInHouse -> "Characters In House"
    }
}