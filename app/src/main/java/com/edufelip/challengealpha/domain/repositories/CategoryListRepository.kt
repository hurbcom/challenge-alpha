package com.edufelip.challengealpha.domain.repositories

import com.edufelip.challengealpha.domain.models.*
import com.edufelip.challengealpha.domain.models.base.PagedList
import kotlinx.coroutines.flow.Flow

interface CategoryListRepository {
    fun getFilmList(search: String?, page: Int): Flow<PagedList<Film>>
    fun getPeopleList(search: String?, page: Int): Flow<PagedList<People>>
    fun getPlanetList(search: String?, page: Int): Flow<PagedList<Planet>>
    fun getSpecieList(search: String?, page: Int): Flow<PagedList<Specie>>
    fun getStarshipList(search: String?, page: Int): Flow<PagedList<Starship>>
    fun getVehicleList(search: String?, page: Int): Flow<PagedList<Vehicle>>
}