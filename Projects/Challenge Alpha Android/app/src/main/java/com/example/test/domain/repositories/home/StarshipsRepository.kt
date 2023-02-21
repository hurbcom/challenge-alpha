package com.example.test.domain.repositories.home

import com.example.core.base.BaseResult
import com.example.test.domain.models.Starship
import kotlinx.coroutines.flow.Flow

interface StarshipsRepository {
    fun getStarships(page: Int, search: String? = null): Flow<BaseResult<List<Starship>>>
}