package com.edufelip.challengealpha.data.local.room

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.edufelip.challengealpha.data.local.room.models.Favorite
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named


@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class FavoriteDaoTest {
    private val defaultFavorite = Favorite(name = "name", url = "url", imageUrl = "imageUrl")

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: FavoriteDatabase
    private lateinit var dao: FavoriteDao

    @Before
    fun setup() {
        hiltRule.inject()
        dao = database.favoriteDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun getAllFavorites() = runTest {
        val favorite = Favorite("yes", "yes", "yes")
        dao.insertFavorite(defaultFavorite)
        dao.insertFavorite(favorite)
        val allNotes = dao.getAllFavorites()
        assertThat(allNotes).hasSize(2)
    }

    @Test
    fun insertFavorite() = runTest {
        dao.insertFavorite(defaultFavorite)
        val allNotes = dao.getAllFavorites()
        assertThat(allNotes).contains(defaultFavorite)
    }

    @Test
    fun deleteFavorite() = runTest {
        dao.insertFavorite(defaultFavorite)
        dao.deleteFavorite(defaultFavorite)
        val allNotes = dao.getAllFavorites()
        assertThat(allNotes).isEmpty()
    }
}