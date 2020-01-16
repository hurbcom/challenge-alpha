package com.ayodkay.alpha.challenge.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HotelsDataViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: HotelsRepo
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allHotels: LiveData<List<Hotels>>

    init
    {
        val hotelsDao = HotelsDatabase.getDatabase(application, viewModelScope).hotelsDao()
        repository = HotelsRepo(hotelsDao)
        allHotels = repository.allHotels
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(hotels: List<Hotels>) = viewModelScope.launch{
        repository.insert(hotels)
    }

    fun nuke() = viewModelScope.launch {
        repository.nukeAllTable()
    }

}