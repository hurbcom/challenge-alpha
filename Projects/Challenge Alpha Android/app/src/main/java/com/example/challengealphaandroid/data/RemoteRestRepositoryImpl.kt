package com.example.challengealphaandroid.data

import com.example.challengealphaandroid.api.RestApi
import com.example.challengealphaandroid.model.StarshipAndPlanetSearchResponse
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException
import javax.inject.Inject

class RemoteRestRepositoryImpl @Inject constructor(private val retrofit: Retrofit): RemoteRestRepository {
    val swService = retrofit.create(RestApi::class.java)

    override suspend fun fetchStarship(name: String): ResultRest<StarshipAndPlanetSearchResponse> {
        return getResponse(
            request = {
                swService.getStarshipId(name)
            },
            defaultErrorMessage = "Error fetching post List"
        )
    }

    override suspend fun fetchPlanet(name: String): ResultRest<StarshipAndPlanetSearchResponse> {
        return getResponse(
            request = {
                swService.getPlanetId(name)
            },
            defaultErrorMessage = "Error fetching user info"
        )
    }


    suspend fun <T> getResponse(
        request: suspend () -> Response<T>,
        defaultErrorMessage: String
    ): ResultRest<T> {
        return try {
            val result = request.invoke()
            if (result.isSuccessful) {
                return ResultRest.success(result.body())
            } else {
                val errorResponse = parseError(result, retrofit)
                ResultRest.error(errorResponse?.status_message ?: defaultErrorMessage, errorResponse)
            }
        } catch (e: Throwable) {
            ResultRest.error("Unknown Error", null)
        }
    }


    fun parseError(response: Response<*>, retrofit: Retrofit): Error? {
        val converter = retrofit.responseBodyConverter<Error>(Error::class.java, arrayOfNulls(0))
        return try {
            converter.convert(response.errorBody()!!)
        } catch (e: IOException) {
            Error()
        }
    }

}

data class ResultRest<out T>(val status: Status, val data: T?, val error: Error?, val message: String?) {

    enum class Status {
        SUCCESS,
        ERROR,
        IN_PROGRESS
    }

    companion object {
        fun <T> success(data: T?): ResultRest<T> {
            return ResultRest(Status.SUCCESS, data, null, null)
        }

        fun <T> error(message: String, error: Error?): ResultRest<T> {
            return ResultRest(Status.ERROR, null, error, message)
        }

        fun <T> inProgress(data: T? = null): ResultRest<T> {
            return ResultRest(Status.IN_PROGRESS, data, null, null)
        }
    }

    override fun toString(): String {
        return "Result(status=$status, data=$data, error=$error, message=$message)"
    }
}

data class Error(val status_code: Int = 0,
                 val status_message: String? = null)