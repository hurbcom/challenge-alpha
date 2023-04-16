package br.com.hurbandroidchallenge.data.local

import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.hurbandroidchallenge.commom.util.date.DateUtils
import br.com.hurbandroidchallenge.data.mapper.characters.charactersEntity
import br.com.hurbandroidchallenge.data.mapper.characters.toPeople
import br.com.hurbandroidchallenge.domain.use_case.characters.SetCharacterLastSeenUseCase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@RunWith(AndroidJUnit4::class)
class LastSeenTest : KoinComponent {

    private val setCharacterLastSeenUseCase: SetCharacterLastSeenUseCase by inject()

    @Test
    fun setCharacterLastSeen() {
        val character = charactersEntity.first()
        runBlocking {
            launch(Dispatchers.IO) {
                setCharacterLastSeenUseCase(character.toPeople()).collectLatest {
                    assert(it.lastSeen == DateUtils.getCurrentDate())
                }
            }
        }
    }

}