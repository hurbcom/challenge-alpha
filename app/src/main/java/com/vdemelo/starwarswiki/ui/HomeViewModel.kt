package com.vdemelo.starwarswiki.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vdemelo.starwarswiki.domain.entity.RequestStatus
import com.vdemelo.starwarswiki.domain.entity.model.ItemsImageUrl
import com.vdemelo.starwarswiki.domain.entity.model.SpeciesList
import com.vdemelo.starwarswiki.domain.entity.model.getImageUrl
import com.vdemelo.starwarswiki.domain.usecase.SpeciesUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val useCase: SpeciesUseCase
) : ViewModel() {

    private val _species = MutableLiveData<RequestStatus<SpeciesList>>()
    val species: LiveData<RequestStatus<SpeciesList>> = _species

    fun fetchSpecies() {
        viewModelScope.launch {
            val requestResult = useCase.fetchSpecies()
            _species.postValue(requestResult)
        }
    }


    // species. url = https://swapi.dev/api/species/1/
    // species image = https://starwars-visualguide.com/assets/img/species/1.jpg

    fun getSpeciesImageUrl(speciesUrl: String?): String? {
        return if (speciesUrl != null) {
            val speciesNumber = getSpeciesNumber(speciesUrl)
            ItemsImageUrl.SPECIES.getImageUrl(speciesNumber)
        } else {
            null
        }
    }

    private fun getSpeciesNumber(speciesUrl: String): String {
        val charsToDrop = if (speciesUrl.endsWith("/")) 1 else 0
        return speciesUrl.dropLast(charsToDrop).takeLastWhile { it.isDigit() }
    }

}
