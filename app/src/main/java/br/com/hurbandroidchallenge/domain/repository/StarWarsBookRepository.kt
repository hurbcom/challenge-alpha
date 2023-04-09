package br.com.hurbandroidchallenge.domain.repository

import br.com.hurbandroidchallenge.domain.model.HomeCategories
import kotlinx.coroutines.flow.Flow

interface StarWarsBookRepository {

    suspend fun getHomeCategories(): Flow<HomeCategories>

}