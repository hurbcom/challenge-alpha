package br.com.mdr.starwars.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.mdr.starwars.rules.CoroutinesTestRule
import br.com.mdr.starwars.rules.MockWebServerRule
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.koin.core.logger.Level
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import java.net.ConnectException

const val REQUEST_ERROR = "Error on request"
private const val IO_DISPATCHER = "IO"

@ExperimentalCoroutinesApi
abstract class BaseViewModelTest : KoinTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    var mockWebServerRule = MockWebServerRule()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger(Level.ERROR)
        modules(
            module {
                single<CoroutineDispatcher>(named(IO_DISPATCHER)) {
                    coroutinesTestRule.testDispatcher
                }
            }
        )
    }

    //protected lateinit var observerLoading: MutableStateFlow<PageState.Loading>

    fun mockErrorWrapper() = ConnectException(REQUEST_ERROR)

//    fun verifyLoaderWasShown() {
//        verifySequence {
////            observerLoading.onChanged(true)
////            observerLoading.onChanged(false)
//        }
//        confirmVerified(observerLoading)
//    }
//
//    fun verifyLoaderWasNotShown() {
//        confirmVerified(observerLoading)
//    }

}
