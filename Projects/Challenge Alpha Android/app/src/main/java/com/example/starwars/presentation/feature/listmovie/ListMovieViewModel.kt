package com.example.starwars.presentation.feature.listmovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.starwars.data.repository.MoviesRepository
import com.example.starwars.presentation.feature.ViewModelItem
import com.example.starwars.presentation.model.Item
import com.example.starwars.presentation.model.toMovieList
import com.example.starwars.retrofit.apiCollect
import kotlinx.coroutines.launch

class ListMovieViewModel(
    private val moviesRepository: MoviesRepository
) : ViewModelItem() {
    override val _nextPageLiveData = MutableLiveData("1")
    override val nextPageLiveData: LiveData<String> = _nextPageLiveData
    override val _previousPageLiveData = MutableLiveData("1")
    override val previousPageLiveData: LiveData<String> = _previousPageLiveData

    override val _actualItemListLiveData: MutableLiveData<List<Item>> =
        MutableLiveData<List<Item>>()
    override val actualItemListLiveData: LiveData<List<Item>> = _actualItemListLiveData

    override val _loadingLiveData = MutableLiveData<Boolean>()
    override val loadingLiveData: LiveData<Boolean> = _loadingLiveData

    override val _requestError = MutableLiveData<String>()
    override val requestError: LiveData<String> = _requestError

    override fun getItems(page: String) {
        viewModelScope.launch {
            moviesRepository.getMovies(page).apiCollect(
                onLoading = {
                    _loadingLiveData.postValue( true)
                },
                onError = {
                    _loadingLiveData.value = false
                    _requestError.value = it.message.toString()
                },
                onSuccessful = { moviePage ->
                    _loadingLiveData.value = false
                    _nextPageLiveData.value = moviePage?.nextPage ?: "0"
                    _previousPageLiveData.value = moviePage?.previousPage ?: "0"
                    _actualItemListLiveData.value = moviePage?.movieList?.toMovieList()
                }
            )
        }
    }
}