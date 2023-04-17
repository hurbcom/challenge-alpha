package br.com.hurbandroidchallenge.data.local

import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.hurbandroidchallenge.data.remote.config.ApiUrls
import br.com.hurbandroidchallenge.domain.use_case.characters.GetCharacterByUrlUseCase
import br.com.hurbandroidchallenge.domain.use_case.characters.SetFavoriteCharacterUseCase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@RunWith(AndroidJUnit4::class)
class FavoriteItemTest : KoinComponent {

    private val setFavoriteCharacterUseCase: SetFavoriteCharacterUseCase by inject()
    private val getCharacterByUrlUseCase: GetCharacterByUrlUseCase by inject()

    @Test
    fun setFavoriteCharacter() {

        runBlocking {
            launch(Dispatchers.IO) {
                getCharacterByUrlUseCase("${ApiUrls.characters}1").collectLatest { character ->
                    setFavoriteCharacterUseCase(character).collect { favoriteCharacter ->
                        assert(favoriteCharacter.favorite)
                    }
                }
            }
        }
    }

}