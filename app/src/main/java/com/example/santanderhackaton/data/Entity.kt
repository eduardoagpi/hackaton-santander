package com.example.santanderhackaton.data

import java.util.Date

const val CHARACTERS_ROUTE = "/api/characters"
const val CHARACTERS_BY_ID_ROUTE = "/api/character/{id}"
const val STUDENTS_ROUTE = "/api/characters/students"
const val STAFF_ROUTE = "/api/characters/staff"
const val SPELLS_ROUTE = "/api/spells"
const val CHARACTERS_IN_HOUSE_ROUTE = "/api/characters/house/{house}"

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
        ServiceEnum.Characters -> CHARACTERS_ROUTE
        ServiceEnum.CharactersByID -> CHARACTERS_BY_ID_ROUTE
        ServiceEnum.Students -> STUDENTS_ROUTE
        ServiceEnum.Staff -> STAFF_ROUTE
        ServiceEnum.Spells -> SPELLS_ROUTE
        ServiceEnum.CharactersInHouse -> CHARACTERS_IN_HOUSE_ROUTE
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