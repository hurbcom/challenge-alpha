package br.com.hurbandroidchallenge.domain.repository

import br.com.hurbandroidchallenge.domain.model.base.PagedList
import kotlinx.coroutines.flow.Flow

interface StarWarsBookRepository<T> {

    fun getItemList(url: String): Flow<PagedList<T>>

    fun getItemByUrl(url: String): Flow<T>

}