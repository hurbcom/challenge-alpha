package com.example.challenge_alpha.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.challenge_alpha.api.*
import com.example.challenge_alpha.data.resultDetail.ResultDetailDao
import com.example.challenge_alpha.model.ResultDetail
import com.example.challenge_alpha.model.Suggestions
import com.example.challenge_alpha.util.MockitoHelper
import com.example.challenge_alpha.util.mock
import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody
import org.junit.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.*
import retrofit2.Response
import kotlin.Result

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class HurbRepositoryTest {


    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    private val resultDetailRepository = mock(ResultDetailRepository::class.java)
    private val hurbService = mock(HurbService::class.java)

    private val hurbRepository = HurbRepository(hurbService, resultDetailRepository)

    private val query = "123"

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()

    }


    @Test
    fun getSearchResult() {


        runBlockingTest {
            `when`(hurbService.search(query, 1)).thenReturn(
                Response.success(
                    HurbResponse(
                        resultDetail = listOf(ResultDetail("123"))
                    )
                )
            )
        }

        hurbRepository.search(query)
        hurbRepository.searchResult().observeForever {
        }

        runBlocking {
            launch {
                verify(hurbService).search(query, 1)
                verify(
                    resultDetailRepository
                ).insertDetail(MockitoHelper.anyObject())
            }
        }

        verifyNoMoreInteractions(hurbService)
        verifyNoMoreInteractions(resultDetailRepository)


    }

    @Test
    fun suggestionSearch() {

        hurbRepository.suggestionSearch("123").observeForever {
        }

        // Response.success(null)

        runBlockingTest {
            `when`(hurbService.suggestion("123")).thenReturn(
                Response.success(
                    HurbSuggestions(
                        listOf(
                            Suggestions("123", country = "brazil")
                        )
                    )
                )
            )

        }

         runBlocking {
            launch {
                verify(hurbService).suggestion("123")
            }
        }


        verifyNoMoreInteractions(hurbService)
    }


    @Test
    fun suggestionSearch_getResult_success() = runBlockingTest {

        `when`(hurbService.suggestion("123")).thenReturn(
            Response.success(
                HurbSuggestions(
                    listOf(
                        Suggestions("123", country = "brazil")
                    )
                )
            )
        )

        val getResult = GetResult.getResult { hurbService.suggestion("123") }
        assertEquals("123", getResult.data?.suggestions?.firstOrNull()?.text)
        assertEquals("brazil", getResult.data?.suggestions?.firstOrNull()?.country)

    }


    @Test
    fun suggestionSearch_getResult_fail() = runBlockingTest {


        `when`(hurbService.suggestion("123")).thenReturn(
            Response.error(
                404,
                "teste"
                    .toResponseBody("application/json; charset=utf-8".toMediaTypeOrNull())
            )
        )


        val getResult = GetResult.getResult { hurbService.suggestion("123") }
        assertEquals(
            "Network call has failed for a following reason:  404 Response.error()",
            getResult.message
        )

    }


    @Test
    fun suggestionSearch_getResult_failNull() = runBlockingTest {


        val getResult = GetResult.getResult { hurbService.suggestion("123") }
        assertEquals(
            "Network call has failed for a following reason: java.lang.NullPointerException",
            getResult.message
        )

    }

    @Test
    fun suggestionSearch_suggestionsLiveData() {

        runBlockingTest {
            `when`(hurbService.suggestion("123")).thenReturn(
                Response.success(
                    HurbSuggestions(
                        listOf(
                            Suggestions("123", country = "brazil")
                        )
                    )
                )
            )

            val getResult = GetResult.getResult { hurbService.suggestion("123") }
            val suggestionLiveData = GetResult.suggestionsLiveData { getResult }

            suggestionLiveData.observeForever {
            }

            Thread.sleep(100)

            assertEquals(
                "123",
                suggestionLiveData.value?.data?.suggestions?.firstOrNull()?.text
            )
            assertEquals(
                "brazil",
                suggestionLiveData.value?.data?.suggestions?.firstOrNull()?.country
            )


        }
    }


}

