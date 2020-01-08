package com.example.challenge_alpha.ui.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.challenge_alpha.repository.FavoritesRepository
import com.example.challenge_alpha.repository.HurbRepository
import com.example.challenge_alpha.repository.LastSeenRepository
import com.example.challenge_alpha.repository.ResultDetailRepository

class ResultsViewModelFactory(
    private val lastSeenRepository: LastSeenRepository,
    private val resultDetailRepository: ResultDetailRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(lastSeenRepository, resultDetailRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}