package com.example.starwars.data.repository

import com.example.starwars.data.model.PeoplePage
import com.example.starwars.data.datasource.PeoplesDatasource
import com.example.starwars.retrofit.ApiResult
import kotlinx.coroutines.flow.Flow

class PeoplesRepositoryImpl(private val peoplesDatasource: PeoplesDatasource):PeoplesRepository {
    override fun getPeoples(page:String): Flow<ApiResult<PeoplePage>> {
        return peoplesDatasource.getPeoples(page)
    }
}