package com.example.test.domain.repositories.home

import com.example.core.base.BaseResult
import com.example.test.domain.models.Planet
import kotlinx.coroutines.flow.Flow

interface PlanetsRepository {
    fun getPlanets(page: Int, search: String? = null): Flow<BaseResult<List<Planet>>>
}