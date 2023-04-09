package br.com.hurbandroidchallenge.domain.use_case

import br.com.hurbandroidchallenge.domain.model.HomeCategories
import br.com.hurbandroidchallenge.domain.repository.StarWarsBookRepository
import kotlinx.coroutines.flow.Flow

class GetHomeCategoriesUseCase(
    private val repository: StarWarsBookRepository
) {

    suspend operator fun invoke(): Flow<HomeCategories> {
        return repository.getHomeCategories()
    }

}