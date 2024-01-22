package com.vdemelo.starwarswiki.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vdemelo.starwarswiki.data.repository.StarWarsRemoteRepositoryImpl
import com.vdemelo.starwarswiki.domain.entity.RequestStatus
import com.vdemelo.starwarswiki.domain.entity.model.Species
import com.vdemelo.starwarswiki.domain.entity.model.SpeciesList
import com.vdemelo.starwarswiki.domain.usecase.HomeUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val useCase: HomeUseCase
) : ViewModel() {

    private val _species = MutableLiveData<Species?>()
    val species: LiveData<Species?> = _species

    fun fetchSpecies() {
        viewModelScope.launch {
            when (
                val requestResult = useCase.fetchSpecies()
            ) {
                is RequestStatus.Success -> {
                    _species.postValue(requestResult.data?.results?.firstOrNull())
                }

                is RequestStatus.Error -> {
                    //TODO give error
                }
            }
        }
    }

}
