package com.example.test.data.repositories

import com.example.test.data.datasources.network.api
import com.example.test.domain.repositories.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val api: api) : Repository {

}