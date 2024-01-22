package com.vdemelo.starwarswiki.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vdemelo.starwarswiki.data.repository.StarWarsRemoteRepositoryImpl
import com.vdemelo.starwarswiki.domain.entity.model.Species
import com.vdemelo.starwarswiki.domain.entity.model.SpeciesList
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: StarWarsRemoteRepositoryImpl
): ViewModel() {

    private val _species = MutableLiveData<Species?>()
    val species: LiveData<Species?> = _species

    fun fetchSpecies() {
        viewModelScope.launch {
            val response = repository.fetchSpecies()
            _species.postValue(SpeciesList(response).results.firstOrNull())
        }
    }

}
