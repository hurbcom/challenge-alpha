package com.br.myapplication.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.myapplication.data.model.Favorite
import com.br.myapplication.data.remote.SafeResponse
import com.br.myapplication.data.remote.safeRequest

import com.br.myapplication.data.repository.favorites.IFavoritesRepository
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val repository: IFavoritesRepository
) : ViewModel() {
    private val _favoriteList = MutableLiveData<List<Favorite>>()

    val favoriteList: LiveData<List<Favorite>>
        get() = _favoriteList

    fun fetchFavorites() {

        viewModelScope.launch {

            when (val response = safeRequest { repository.getFavoritesList() }) {
                is SafeResponse.Success -> {

                    response.value.let {
                        _favoriteList.value = it
                    }
                }
                else -> {
                }
            }
        }
    }

}