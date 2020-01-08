package com.example.challenge_alpha.ui.favorites

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.challenge_alpha.db.favorites.FavoritesDataBase
import com.example.challenge_alpha.db.lastSeen.LastSeenDataBase
import com.example.challenge_alpha.db.resultDetail.ResultDetailDataBase
import com.example.challenge_alpha.repository.FavoritesRepository
import com.example.challenge_alpha.repository.LastSeenRepository
import com.example.challenge_alpha.repository.ResultDetailRepository
import java.util.concurrent.Executors

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