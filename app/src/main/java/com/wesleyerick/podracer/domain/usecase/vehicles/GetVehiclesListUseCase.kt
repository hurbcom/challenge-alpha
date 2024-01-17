package com.wesleyerick.podracer.domain.usecase.vehicles

import android.content.Context
import com.wesleyerick.podracer.R
import com.wesleyerick.podracer.data.repository.vehicles.IRepositoryVehicles
import com.wesleyerick.podracer.util.Result
import com.wesleyerick.podracer.util.safeRunDispatcher

class GetVehiclesListUseCase(
    private val repository: IRepositoryVehicles,
    private val context: Context,
) {
    suspend operator fun invoke() = when (
        val result = safeRunDispatcher {
            repository.getAll()
        }
    ) {
        is Result.Success -> {
            val list = if (!result.data.body()?.vehiclesList.isNullOrEmpty()) {
                result.data.body()?.vehiclesList
            } else {
                emptyList()
            }
            Result.Success(list)
        }

        is Result.Failure -> Result.Failure(result.error, context.getString(R.string.error_message))
    }
}
