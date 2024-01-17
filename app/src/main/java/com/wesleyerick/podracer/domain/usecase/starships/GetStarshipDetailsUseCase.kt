package com.wesleyerick.podracer.domain.usecase.starships

import android.content.Context
import com.wesleyerick.podracer.R
import com.wesleyerick.podracer.data.repository.starships.IRepositoryStarships
import com.wesleyerick.podracer.util.Result
import com.wesleyerick.podracer.util.safeRunDispatcher

class GetStarshipDetailsUseCase(
    private val repository: IRepositoryStarships,
    private val context: Context,
) {
    suspend operator fun invoke(id: String) = when (
        val result = safeRunDispatcher {
            repository.getDetails(id)
        }
    ) {
        is Result.Success -> {
            Result.Success(result.data.body())
        }

        is Result.Failure -> Result.Failure(result.error, context.getString(R.string.error_message))
    }
}