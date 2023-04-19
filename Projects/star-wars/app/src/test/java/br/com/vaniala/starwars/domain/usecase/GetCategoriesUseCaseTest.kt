package br.com.vaniala.starwars.domain.usecase

import br.com.vaniala.starwars.categoriesListMock
import br.com.vaniala.starwars.categoriesResultMock
import br.com.vaniala.starwars.core.Result
import br.com.vaniala.starwars.core.StatusConnectivity
import br.com.vaniala.starwars.domain.model.CategoryResponse
import br.com.vaniala.starwars.domain.repository.CategoryRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifyAll
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

    private val categoryRepository: CategoryRepository = mockk()
    private val saveCategoriesInBDUseCase: SaveCategoriesInBDUseCase = mockk()
    private val getCategoriesFromBDUseCase: GetCategoriesFromBDUseCase = mockk()
    private val statusConnectivity: StatusConnectivity = mockk()
    private lateinit var getCategoriesUseCase: GetCategoriesUseCase

    @Before
    fun setUp() {
        getCategoriesUseCase =
            GetCategoriesUseCase(
                categoryRepository,
                saveCategoriesInBDUseCase,
                getCategoriesFromBDUseCase,
                statusConnectivity,
            )
    }

    @Test
    fun `should emit loading result initially when get from db`() = runTest {
        coEvery { categoryRepository.isUpdate() } returns false
        coEvery { statusConnectivity.isNotConnected() } returns true
        coEvery { getCategoriesFromBDUseCase() } returns emptyList()

        val resultIsUpdateFalseAndIsNotConnectedIsTrue = getCategoriesUseCase().first()

        assertTrue(resultIsUpdateFalseAndIsNotConnectedIsTrue is Result.Loading)

        coVerifyAll {
            categoryRepository.isUpdate()
            statusConnectivity.isNotConnected()
            getCategoriesFromBDUseCase()
        }

        coEvery { categoryRepository.isUpdate() } returns true
        coEvery { statusConnectivity.isNotConnected() } returns false
        coEvery { getCategoriesFromBDUseCase() } returns emptyList()

        val resultIsUpdateTrueAndIsNotConnectedIsFalse = getCategoriesUseCase().first()
        assertTrue(resultIsUpdateTrueAndIsNotConnectedIsFalse is Result.Loading)

        coVerify {
            categoryRepository.isUpdate()
            statusConnectivity.isNotConnected()
            getCategoriesFromBDUseCase()
        }

        coEvery { categoryRepository.isUpdate() } returns true
        coEvery { statusConnectivity.isNotConnected() } returns true
        coEvery { getCategoriesFromBDUseCase() } returns emptyList()

        val resultIsUpdateAndIsNotConnectedIsAreTrue = getCategoriesUseCase().first()
        assertTrue(resultIsUpdateAndIsNotConnectedIsAreTrue is Result.Loading)

        coVerifyAll {
            categoryRepository.isUpdate()
            statusConnectivity.isNotConnected()
            getCategoriesFromBDUseCase()
        }
    }

    @Test
    fun `should emit loading result initially when get from remote and isSuccessful is true`() = runTest {
        val responseMock = mockk<Response<CategoryResponse>>()

        coEvery { categoryRepository.isUpdate() } returns false
        coEvery { statusConnectivity.isNotConnected() } returns false
        coEvery { categoryRepository.getCategories() } returns responseMock
        coEvery { saveCategoriesInBDUseCase(categoriesListMock) } returns Unit

        every { responseMock.isSuccessful } returns true
        every { responseMock.body() } returns categoriesResultMock

        val resultIsSuccessfulIsTrue = getCategoriesUseCase.invoke().first()
        assertTrue(resultIsSuccessfulIsTrue is Result.Loading)

        coVerifyAll {
            categoryRepository.isUpdate()
            categoryRepository.isUpdate()
            saveCategoriesInBDUseCase(categoriesListMock)
            categoryRepository.getCategories()
        }
        verifyAll {
            responseMock.isSuccessful
            responseMock.body()
        }
    }

    @Test
    fun `should emit loading result initially when get from remote and isSuccessful is false`() = runTest {
        val responseMock = mockk<Response<CategoryResponse>>()

        coEvery { categoryRepository.isUpdate() } returns false
        coEvery { statusConnectivity.isNotConnected() } returns false
        coEvery { categoryRepository.getCategories() } returns responseMock

        every { responseMock.isSuccessful } returns false

        val resultIsSuccessfulIsTrue = getCategoriesUseCase.invoke().first()
        assertTrue(resultIsSuccessfulIsTrue is Result.Loading)

        coVerifyAll {
            categoryRepository.isUpdate()
            categoryRepository.isUpdate()
            categoryRepository.getCategories()
        }
        verifyAll {
            responseMock.isSuccessful
        }
    }

    @Test
    fun `should emit success with category list when response isSuccessful equals true`() = runTest {
        val responseMock = mockk<Response<CategoryResponse>>()

        coEvery { categoryRepository.isUpdate() } returns false
        coEvery { statusConnectivity.isNotConnected() } returns false
        coEvery { categoryRepository.getCategories() } returns responseMock
        coEvery { saveCategoriesInBDUseCase(categoriesListMock) } returns Unit

        every { responseMock.isSuccessful } returns true
        every { responseMock.body() } returns categoriesResultMock

        val result = getCategoriesUseCase.invoke()
        result.drop(1).collect { value ->
            assertTrue(value is Result.Success)
            assertEquals(categoriesListMock, (value as Result.Success).data)
        }

        coVerifyAll {
            categoryRepository.isUpdate()
            categoryRepository.isUpdate()
            saveCategoriesInBDUseCase(categoriesListMock)
            categoryRepository.getCategories()
        }
        verifyAll {
            responseMock.isSuccessful
            responseMock.body()
        }
    }

    @Test
    fun `should emit failure with message Error getting categories when response isSuccessful equals false`() =
        runTest {
            val body = null
            val responseMock = mockk<Response<CategoryResponse>>()

            coEvery { categoryRepository.isUpdate() } returns false
            coEvery { statusConnectivity.isNotConnected() } returns false

            coEvery { categoryRepository.getCategories() } returns responseMock
            every { responseMock.isSuccessful } returns false
            every { responseMock.body() } returns body

            val result = getCategoriesUseCase.invoke()
            result.drop(1).collect { value ->
                assertTrue(value is Result.Failure)
                assertNull(body)
                assertEquals("Error getting categories", (value as Result.Failure).msg?.message)
            }
            coVerifyAll {
                categoryRepository.isUpdate()
                categoryRepository.isUpdate()
                categoryRepository.getCategories()
            }
            verify { responseMock.isSuccessful }
            verify(exactly = 0) { responseMock.body() }
        }

    @Test
    fun `should emit failure with message Error exception when response throws exception`() = runTest {
        coEvery { categoryRepository.getCategories() } throws Exception("Error exception")
        coEvery { categoryRepository.isUpdate() } returns false
        coEvery { statusConnectivity.isNotConnected() } returns false

        val result = getCategoriesUseCase.invoke()

        result.drop(1).collect { value ->
            assertTrue(value is Result.Failure)
            assertEquals("Error exception", (value as Result.Failure).msg?.cause?.message)
        }
        coVerifyAll {
            categoryRepository.isUpdate()
            categoryRepository.isUpdate()
            categoryRepository.getCategories()
        }
    }
}
