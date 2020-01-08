package com.example.challenge_alpha.ui.resultDetail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.challenge_alpha.repository.FavoritesRepository
import com.example.challenge_alpha.repository.LastSeenRepository
import com.example.challenge_alpha.repository.ResultDetailRepository

class ResultDetailViewModelFactory(
    private val lastSeenRepository: LastSeenRepository,
    private val favoritesRepository: FavoritesRepository,
    private val resultDetailRepository: ResultDetailRepository,
    private val sku: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ResultDetailViewModel(lastSeenRepository, favoritesRepository, resultDetailRepository, sku) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}