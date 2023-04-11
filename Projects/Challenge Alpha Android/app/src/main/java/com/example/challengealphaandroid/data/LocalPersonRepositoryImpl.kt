package com.example.challengealphaandroid.data

import com.example.challengealphaandroid.data.room.PersonDao
import com.example.challengealphaandroid.model.Person
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalPersonRepositoryImpl @Inject constructor(private val personDao: PersonDao) : LocalPersonRepository {
    override suspend fun getPeopleCache(): List<Person> {
        return personDao.getAll()
    }

    override suspend fun savePeople(people: List<Person>) {
        personDao.insertAll(people)
    }

    override suspend fun savePerson(person: Person) {
        personDao.insert(person)
    }

    suspend override fun updatePerson(person: Person) {
        personDao.update(person)
    }

    suspend override fun deleteAll() {
        personDao.deleteAll()
    }
}