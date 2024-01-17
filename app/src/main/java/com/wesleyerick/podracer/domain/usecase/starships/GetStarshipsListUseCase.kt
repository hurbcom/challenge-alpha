package com.wesleyerick.podracer.domain.usecase.starships

import android.content.Context
import com.wesleyerick.podracer.R
import com.wesleyerick.podracer.data.repository.starships.IRepositoryStarships
import com.wesleyerick.podracer.util.Result
import com.wesleyerick.podracer.util.safeRunDispatcher

class GetStarshipsListUseCase(
    private val repository: IRepositoryStarships,
    private val context: Context,
) {
    suspend operator fun invoke() = when (
        val result = safeRunDispatcher {
            repository.getAll()
        }
    ) {
        is Result.Success -> {
            val list = if (!result.data.body()?.starshipsList.isNullOrEmpty()) {
                result.data.body()?.starshipsList
            } else {
                emptyList()
            }
            Result.Success(list)
        }

        is Result.Failure -> Result.Failure(result.error, context.getString(R.string.error_message))
    }
}