package com.example.challenge_alpha.ui.favorites

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.challenge_alpha.data.favorites.FavoritesDataBase
import com.example.challenge_alpha.repository.FavoritesRepository

object Injection {

    private fun provideFavoritesRepository(context: Context): FavoritesRepository {
        val dao = FavoritesDataBase.getInstance(context).favoritesDao()
        return FavoritesRepository(dao)
    }


    fun provideViewModelFactory(context: Context): ViewModelProvider.Factory {
        return FavoritesViewModelFactory(
            provideFavoritesRepository(context)
        )
    }

}