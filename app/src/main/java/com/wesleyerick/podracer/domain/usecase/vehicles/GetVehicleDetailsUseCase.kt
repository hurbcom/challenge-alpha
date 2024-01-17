package com.wesleyerick.podracer.domain.usecase.vehicles

import android.content.Context
import com.wesleyerick.podracer.R
import com.wesleyerick.podracer.data.repository.IRepositoryVehicles
import com.wesleyerick.podracer.util.Result
import com.wesleyerick.podracer.util.safeRunDispatcher

class GetVehicleDetailsUseCase(
    private val repository: IRepositoryVehicles,
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