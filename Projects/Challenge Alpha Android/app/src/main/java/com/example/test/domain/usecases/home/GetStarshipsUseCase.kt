package com.example.test.domain.usecases.home

import com.example.core.base.BaseResult
import com.example.core.base.UseCaseWithParams
import com.example.test.domain.models.Starship
import com.example.test.domain.repositories.home.StarshipsRepository
import com.example.test.domain.usecases.ListGetParams
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStarshipsUseCase @Inject constructor(
    private val starshipsRepository: StarshipsRepository
) :
    UseCaseWithParams<ListGetParams, Flow<BaseResult<List<Starship>>>>() {
    override fun performAction(requestData: ListGetParams): Flow<BaseResult<List<Starship>>> {
        with(requestData) {
            return starshipsRepository.getStarships(page, search)
        }
    }
}