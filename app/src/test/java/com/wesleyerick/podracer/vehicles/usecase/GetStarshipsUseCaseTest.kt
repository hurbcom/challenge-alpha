package com.wesleyerick.podracer.vehicles.usecase

import android.content.Context
import com.wesleyerick.podracer.CoroutinesTestRule
import com.wesleyerick.podracer.data.model.ListData
import com.wesleyerick.podracer.data.repository.starships.StarshipsRepository
import com.wesleyerick.podracer.domain.usecase.starships.GetStarshipsListUseCase
import com.wesleyerick.podracer.util.Result
import io.mockk.called
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class GetStarshipsUseCaseTest {

    private companion object {
        const val MOCK_EXCEPTION = "Error mockk"
        const val MOCK_COUNT = 0
        const val MOCK_EXACTLY = 1
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineTestRule = CoroutinesTestRule()

    private lateinit var getStarshipsListUseCase: GetStarshipsListUseCase
    private val starshipsRepository: StarshipsRepository = mockk(relaxed = true)

    @Before
    fun setup() {
        val context = mockk<Context>()
        getStarshipsListUseCase = GetStarshipsListUseCase(starshipsRepository, context)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when execute api getAll() return mock success`() = runTest {
        coEvery { starshipsRepository.getAll() } returns Response
            .success(
                ListData(
                    count = MOCK_COUNT,
                    next = String(),
                    previous = String,
                    results = emptyList()
                )
            )

        getStarshipsListUseCase()

        coVerify(exactly = MOCK_EXACTLY) { Result.Success<Any>(any()) }
        verify { Result.Failure(any()) wasNot called }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when execute api getAll return mock error`() = runTest {
        coEvery { starshipsRepository.getAll() } throws Exception(MOCK_EXCEPTION)

        getStarshipsListUseCase()

        coVerify(exactly = MOCK_EXACTLY) { Result.Failure(any()) }
        verify { Result.Success<Any>(any()) wasNot called }
    }
}