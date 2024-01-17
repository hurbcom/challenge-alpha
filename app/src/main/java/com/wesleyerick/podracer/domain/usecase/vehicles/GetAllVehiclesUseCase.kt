package com.wesleyerick.podracer.domain.usecase.vehicles

import android.content.Context
import com.wesleyerick.podracer.R
import com.wesleyerick.podracer.data.repository.IRepositoryVehicles
import com.wesleyerick.podracer.util.Result
import com.wesleyerick.podracer.util.safeRunDispatcher

class GetAllVehiclesUseCase(
    private val repository: IRepositoryVehicles,
    private val context: Context,
) {
    suspend operator fun invoke() = when (
        val result = safeRunDispatcher {
            repository.getAll()
        }
    ) {
        is Result.Success -> {
            val list = if (!result.data.body()?.results.isNullOrEmpty()) {
                result.data.body()?.results
            } else {
                emptyList()
            }
            Result.Success(list)
        }

        is Result.Failure -> Result.Failure(result.error, context.getString(R.string.error_message))
    }
}
