package com.filipeoliveira.hurbchallenge.ui.favorite

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filipeoliveira.hurbchallenge.R
import com.filipeoliveira.hurbchallenge.data.HotelRepository
import com.filipeoliveira.hurbchallenge.ui.UIState
import com.filipeoliveira.hurbchallenge.ui.model.HotelUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HotelFavoriteListViewModel(
    val repository: HotelRepository
) : ViewModel() {

    private val _hotelList = MutableLiveData<UIState<List<HotelUI>>>()
    val hotelList: LiveData<UIState<List<HotelUI>>>
        get() = _hotelList

    private val _isInFavorite = MutableLiveData<UIState<Boolean>>()
    val isInFavorite: LiveData<UIState<Boolean>>
        get() = _isInFavorite

    private val _databaseOperation = MutableLiveData<UIState<@androidx.annotation.StringRes Int>>()
    val databaseOperation: LiveData<UIState<Int>>
        get() = _databaseOperation

    fun loadFavoriteHotels() {
        _hotelList.value = UIState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getFavoriteHotels()
                _hotelList.postValue(UIState.Success(response))
            } catch (e: Exception) {
                Log.e(TAG, e.toString())
                _hotelList.postValue(UIState.Error(R.string.hint_generic_network_error))
            }
        }
    }

    fun checkIfItsInFavorites(hotelUI: HotelUI){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.isFavorite(hotelUI)
                _isInFavorite.postValue(UIState.Success(response))
            } catch (e: Exception){
                Log.e(TAG, e.toString())
                _isInFavorite.postValue(UIState.Error(R.string.hint_operation_error_generic))
            }
        }
    }

    fun removeFromFavorite(hotelUI: HotelUI){
        _databaseOperation.value = UIState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.removeFromFavorites(hotelUI)
                _databaseOperation.postValue(UIState.Success(R.string.feedback_delete_favorite_success))
            } catch (e: Exception){
                Log.e(TAG, e.toString())
                _databaseOperation.postValue(UIState.Error(R.string.feedback_delete_favorite_error))
            }
        }
    }

    fun addToFavorite(hotelUI: HotelUI){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.addToFavorites(hotelUI)
                _databaseOperation.postValue(UIState.Success(R.string.feedback_save_favorite_success))
            } catch (e: Exception){
                Log.e(TAG, e.toString())
                _databaseOperation.postValue(UIState.Error(R.string.feedback_save_favorite_error))
            }
        }
    }

    companion object {
        const val TAG = "HotelFavoriteListViewModel"
    }
}