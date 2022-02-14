package com.isranascimento.network.response.util

import com.google.common.truth.Truth.assertThat
import com.isranascimento.network.response.NetworkResponse
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import retrofit2.Response
import java.io.IOException

class NetworkResponseTransformTest {
    @Test
    fun `WHEN networkCall is Success THEN withNetworkResponse returns NetworkResponse_Success`() {
        val response = withNetworkResponse {
            Response.success(null)
        }

        assertThat(response).isInstanceOf(NetworkResponse.Success::class.java)
    }

    @Test
    fun `WHEN networkCall is Error THEN withNetworkResponse returns NetworkResponse_GenericError`() {
        val response: NetworkResponse<Nothing> = withNetworkResponse {
            Response.error(500, "{}".toResponseBody())
        }
        assertThat(response).isInstanceOf(NetworkResponse.GenericError::class.java)
    }

    @Test
    fun `WHEN networkCall throws an Exception THEN withNetworkResponse returns NetworkResponse_GenericError`() {
        val response: NetworkResponse<Nothing> = withNetworkResponse {
            throw IOException()
        }
        assertThat(response).isInstanceOf(NetworkResponse.GenericError::class.java)
    }
}