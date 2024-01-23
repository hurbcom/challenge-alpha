package com.br.myapplication.ui.species

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.br.myapplication.data.dao.SpeciesDao
import com.br.myapplication.data.model.Specie
import com.br.myapplication.data.repository.specie.ISpeciesRepository

class SpeciesViewModel(
    repository: ISpeciesRepository,
    speciesDao: SpeciesDao
) : ViewModel() {

    private val _specieList = MutableLiveData<PagingData<Specie>>()

    val specieList: LiveData<PagingData<Specie>>
        get() = _specieList

}