package br.com.hurbandroidchallenge.data.local

import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.hurbandroidchallenge.data.remote.config.ApiUrls
import br.com.hurbandroidchallenge.domain.model.People
import br.com.hurbandroidchallenge.domain.use_case.characters.GetCharactersUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@RunWith(AndroidJUnit4::class)
class TestLoadingCharacters : KoinComponent {

    private val getCharactersUseCase: GetCharactersUseCase by inject()

    @Test
    fun getAllCharacters() {
        val totalCount = 82
        runBlocking {
            val characters = mutableListOf<People>()
            getCharactersUseCase(ApiUrls.characters, clearLocalDataSource = false).collect { firstPage ->
                characters.addAll(firstPage.results)
                var nextPage = firstPage.next
                while(nextPage != null) {
                    getCharactersUseCase(nextPage).collect {
                        characters.addAll(firstPage.results)
                        nextPage = it.next
                    }
                }
                assert(characters.size == totalCount)
            }
        }
    }

}