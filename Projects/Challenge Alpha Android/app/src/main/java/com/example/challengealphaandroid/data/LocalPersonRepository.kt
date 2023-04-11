package com.example.challengealphaandroid.data

import com.example.challengealphaandroid.model.Person

interface LocalPersonRepository {
    suspend fun getPeopleCache(): List<Person>
    suspend fun savePeople(people: List<Person>)
    suspend fun savePerson(person: Person)
    suspend fun updatePerson(person: Person)
    suspend fun deleteAll()
}