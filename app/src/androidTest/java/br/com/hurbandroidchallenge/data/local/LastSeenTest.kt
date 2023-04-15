package br.com.hurbandroidchallenge.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.hurbandroidchallenge.commom.util.date.DateUtils
import br.com.hurbandroidchallenge.data.local.dao.StarWarsBookDao
import br.com.hurbandroidchallenge.data.local.data_source.StarWarsBookLocalDataSource
import br.com.hurbandroidchallenge.data.local.database.StarWarsBookDatabase
import br.com.hurbandroidchallenge.data.local.model.UpdateEntity
import br.com.hurbandroidchallenge.data.mapper.characters.charactersEntity
import br.com.hurbandroidchallenge.data.mapper.characters.toEntity
import br.com.hurbandroidchallenge.data.mapper.characters.toPeople
import br.com.hurbandroidchallenge.data.repository.CharactersRepository
import br.com.hurbandroidchallenge.domain.use_case.characters.GetLastSeenCharactersUseCase
import br.com.hurbandroidchallenge.domain.use_case.characters.SetCharacterLastSeenUseCase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@RunWith(AndroidJUnit4::class)
class LastSeenTest : KoinComponent {

    private val setCharacterLastSeenUseCase: SetCharacterLastSeenUseCase by inject()
    private val getLastSeenCharactersUseCase: GetLastSeenCharactersUseCase by inject()
    private val repository: CharactersRepository by inject()
    private val localDataSource: StarWarsBookLocalDataSource by inject()

    @Test
    fun setCharacterLastSeen() {
        val character = charactersEntity.first()
        runBlocking {
            launch(Dispatchers.IO) {
//                setCharacterLastSeenUseCase(character.toPeople())
                val markedCharacter = character.toPeople().copy(lastSeen = DateUtils.getCurrentDate())
                repository.updateItem(markedCharacter)
                getLastSeenCharactersUseCase().collectLatest {
                    assert(it.isNotEmpty())
                }
            }
        }
    }

}