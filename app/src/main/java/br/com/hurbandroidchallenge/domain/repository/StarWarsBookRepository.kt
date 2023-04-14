package br.com.hurbandroidchallenge.domain.repository

import br.com.hurbandroidchallenge.domain.model.base.PagedList
import kotlinx.coroutines.flow.Flow

interface StarWarsBookRepository<T> {

    fun getItemList(url: String, clearLocalDatasource: Boolean): Flow<PagedList<T>>

    fun getItemByUrl(url: String): Flow<T>

    fun updateItem(item: T): Flow<Unit>

    fun getFavoriteItems(): Flow<List<T>>

    fun getLastSeenItems(): Flow<List<T>>

}