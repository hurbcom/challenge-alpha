package br.com.hurbandroidchallenge.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.hurbandroidchallenge.data.local.dao.StarWarsBookDao
import br.com.hurbandroidchallenge.data.local.database.StarWarsBookDatabase
import br.com.hurbandroidchallenge.data.local.model.UpdateEntity
import br.com.hurbandroidchallenge.data.mapper.characters.charactersEntity
import kotlinx.coroutines.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LocalDataSourceTest {

    private lateinit var database: StarWarsBookDatabase
    private lateinit var dao: StarWarsBookDao

    @Before
    fun setupDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            StarWarsBookDatabase::class.java
        ).fallbackToDestructiveMigration().build()
        dao = database.dao
    }

    @Test
    fun setCharacterFavorite() {
        val characters = charactersEntity
        runBlocking {
            launch(Dispatchers.IO) {
                dao.insertNewCharacters(characters)
                val localCharacters = dao.getCharacters()

                dao.updateCharacter(localCharacters.first().run {
                    UpdateEntity(
                        id = id,
                        favorite = true,
                        lastSeen = lastSeen
                    )
                })
                val newLocalCharacters = dao.getCharacters()

                assert(localCharacters.size == newLocalCharacters.size)
                assert(newLocalCharacters.first().favorite)
            }
        }
    }

    @After
    fun closeDatabase() {
        database.close()
    }

}