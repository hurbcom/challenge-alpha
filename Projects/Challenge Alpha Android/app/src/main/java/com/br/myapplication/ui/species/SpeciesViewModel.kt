package com.br.myapplication.ui.species

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.br.myapplication.data.dao.SpeciesDao
import com.br.myapplication.data.model.Specie
import com.br.myapplication.data.remote.SpeciesPagingSource
import com.br.myapplication.data.repository.specie.ISpeciesRepository

class SpeciesViewModel(
    val repository: ISpeciesRepository,
    val speciesDao: SpeciesDao
) : ViewModel() {

    private val _specieList = Pager(
        config = PagingConfig(pageSize = 10),
        initialKey = 1
    ) {
        SpeciesPagingSource(repository, speciesDao)
    }.flow.cachedIn(viewModelScope).asLiveData()

    val specieList: LiveData<PagingData<Specie>>
        get() = _specieList
}