package com.example.challenge_alpha.ui.results

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.challenge_alpha.repository.FavoritesRepository
import com.example.challenge_alpha.repository.HurbRepository
import com.example.challenge_alpha.repository.LastSeenRepository
import com.example.challenge_alpha.repository.ResultDetailRepository

class ResultsViewModelFactory(
    private val hurbRepository: HurbRepository,
    private val query: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ResultsViewModel(hurbRepository, query) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}