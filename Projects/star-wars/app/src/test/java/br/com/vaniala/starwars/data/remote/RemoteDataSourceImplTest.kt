package br.com.vaniala.starwars.data.remote

import br.com.vaniala.starwars.categoriesResultPopulateMock
import br.com.vaniala.starwars.data.remote.datasource.RemoteDataSourceImpl
import br.com.vaniala.starwars.data.remote.service.ApiService
import br.com.vaniala.starwars.domain.model.CategoryResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import retrofit2.Response
/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 10/04/23.
 *
 */
@OptIn(ExperimentalCoroutinesApi::class)
class RemoteDataSourceImplTest {

    private lateinit var remoteDataSourceImpl: RemoteDataSourceImpl
    private lateinit var service: ApiService

    @Before
    fun setUp() {
        service = mockk()
        remoteDataSourceImpl = RemoteDataSourceImpl(service)
    }

    @Test
    fun `should return categoriesResultPopulateMock when response is successful`() = runTest {
        val expectedBody = categoriesResultPopulateMock
        val expectedResponse = Response.success(expectedBody)
        coEvery { service.getCategories() } returns expectedResponse

        val result = remoteDataSourceImpl.getCategories()

        assertEquals(expectedBody, result.body())
        coVerify { service.getCategories() }
    }

    @Test
    fun `should return errorBody and body null and code 400 and message Error request when response is error`() =
        runTest {
            val expectedErrorBody: ResponseBody = "Error request".toResponseBody("text/plain".toMediaType())
            val expectedErrorResponse = Response.error<CategoryResponse>(400, expectedErrorBody)
            coEvery { service.getCategories() } returns expectedErrorResponse

            val result = remoteDataSourceImpl.getCategories()

            assertNotNull(result.errorBody())
            assertNull(result.body())
            assertEquals(null, result.body())
            assertEquals(400, result.code())
            assertEquals("Response.error()", result.message())
            assertEquals("Error request", result.errorBody()?.string())
            coVerify { service.getCategories() }
        }
}
