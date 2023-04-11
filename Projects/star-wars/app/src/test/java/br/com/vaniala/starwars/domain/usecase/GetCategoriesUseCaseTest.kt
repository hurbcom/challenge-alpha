package br.com.vaniala.starwars.domain.usecase

import br.com.vaniala.starwars.categoriesListMock
import br.com.vaniala.starwars.categoriesResultMock
import br.com.vaniala.starwars.core.Result
import br.com.vaniala.starwars.domain.model.CategoryResult
import br.com.vaniala.starwars.domain.repository.CategoryRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Response

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 10/04/23.
 *
 */
@OptIn(ExperimentalCoroutinesApi::class)
class GetCategoriesUseCaseTest {

    private lateinit var categoryRepository: CategoryRepository
    private lateinit var getCategoriesUseCase: GetCategoriesUseCase

    @Before
    fun setUp() {
        categoryRepository = mockk()
        getCategoriesUseCase = GetCategoriesUseCase(categoryRepository)
    }

    @Test
    fun `should emit firstly loading when response isSuccessful equals true`() = runTest {
        val responseMock = mockk<Response<CategoryResult>>()

        coEvery { categoryRepository.getCategories() } returns responseMock
        every { responseMock.isSuccessful } returns true
        every { responseMock.body() } returns categoriesResultMock

        val result = getCategoriesUseCase.invoke().first()
        assertTrue(result is Result.Loading)

        coVerify { categoryRepository.getCategories() }
        verifyAll {
            responseMock.isSuccessful
            responseMock.body()
        }
    }

    @Test
    fun `should emit firstly loading when response isSuccessful equals false`() = runTest {
        val responseMock = mockk<Response<CategoryResult>>()

        coEvery { categoryRepository.getCategories() } returns responseMock
        every { responseMock.isSuccessful } returns false

        val result = getCategoriesUseCase.invoke().first()
        assertTrue(result is Result.Loading)

        coVerify { categoryRepository.getCategories() }
        verifyAll {
            responseMock.isSuccessful
        }
    }

    @Test
    fun `should emit success with category list when response isSuccessful equals true`() = runTest {
        val responseMock = mockk<Response<CategoryResult>>()

        coEvery { categoryRepository.getCategories() } returns responseMock
        every { responseMock.isSuccessful } returns true
        every { responseMock.body() } returns categoriesResultMock

        val result = getCategoriesUseCase.invoke()
        result.drop(1).collect { value ->
            assertTrue(value is Result.Success)
            assertEquals(categoriesListMock, (value as Result.Success).data)
        }

        coVerify { categoryRepository.getCategories() }
        verifyAll {
            responseMock.isSuccessful
            responseMock.body()
        }
    }

    @Test
    fun `should emit failure with message Error getting categories when response isSuccessful equals false`() =
        runTest {
            val body = null
            val responseMock = mockk<Response<CategoryResult>>()

            coEvery { categoryRepository.getCategories() } returns responseMock
            every { responseMock.isSuccessful } returns false
            every { responseMock.body() } returns body

            val result = getCategoriesUseCase.invoke()
            result.drop(1).collect { value ->
                assertTrue(value is Result.Failure)
                assertNull(body)
                assertEquals("Error getting categories", (value as Result.Failure).msg?.message)
            }
            coVerify { categoryRepository.getCategories() }
            verify { responseMock.isSuccessful }
        }

    @Test
    fun `should emit failure with message Error exception when response throws exception`() = runTest {
        coEvery { categoryRepository.getCategories() } throws Exception("Error exception")

        val result = getCategoriesUseCase.invoke()

        result.drop(1).collect { value ->
            assertTrue(value is Result.Failure)
            assertEquals("Error exception", (value as Result.Failure).msg?.cause?.message)
        }
        coVerify { categoryRepository.getCategories() }
    }
}
