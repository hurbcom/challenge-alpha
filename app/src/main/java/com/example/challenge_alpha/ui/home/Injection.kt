package com.example.challenge_alpha.ui.home

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

    private fun provideLastSeenRepository(context: Context): LastSeenRepository {
        val dao = LastSeenDataBase.getInstance(context).lastSeenDao()
        return LastSeenRepository(dao)
    }

    private fun provideResultDetailRepository(context: Context): ResultDetailRepository {
        val dao = ResultDetailDataBase.getInstance(context).resultDetailDao()
        return ResultDetailRepository(dao)
    }

    fun provideViewModelFactory(context: Context): ViewModelProvider.Factory {
        return ResultsViewModelFactory(
            provideLastSeenRepository(context),
            provideResultDetailRepository(context)
        )
    }

}