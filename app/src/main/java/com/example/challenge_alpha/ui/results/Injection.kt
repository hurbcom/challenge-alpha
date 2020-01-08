package com.example.challenge_alpha.ui.results

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.challenge_alpha.api.HurbService
import com.example.challenge_alpha.db.favorites.FavoritesDataBase
import com.example.challenge_alpha.db.lastSeen.LastSeenDataBase
import com.example.challenge_alpha.db.resultDetail.ResultDetailDataBase
import com.example.challenge_alpha.repository.FavoritesRepository
import com.example.challenge_alpha.repository.HurbRepository
import com.example.challenge_alpha.repository.LastSeenRepository
import com.example.challenge_alpha.repository.ResultDetailRepository
import java.util.concurrent.Executors

object Injection {

    private fun provideRepositories(context: Context): HurbRepository {
        val dao = ResultDetailDataBase.getInstance(context).resultDetailDao()
        return HurbRepository(HurbService.create(), ResultDetailRepository(dao))
    }


    fun provideViewModelFactory(context: Context, query: String): ViewModelProvider.Factory {
        return ResultsViewModelFactory(
            provideRepositories(context),
            query
        )
    }

}