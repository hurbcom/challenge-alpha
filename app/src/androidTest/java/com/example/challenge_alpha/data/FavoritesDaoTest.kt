package com.example.challenge_alpha.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.challenge_alpha.data.favorites.FavoritesDao
import com.example.challenge_alpha.data.favorites.FavoritesDataBase
import com.example.challenge_alpha.model.ResultDetail
import com.example.challenge_alpha.model.ResultDetailAmenities
import com.example.challenge_alpha.model.ResultDetailGallery
import com.example.challenge_alpha.model.ResultDetailTaxes
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import java.io.IOException
import java.util.*

@RunWith(AndroidJUnit4::class)
class FavoritesDaoTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()


    private lateinit var favoritesDatabase: FavoritesDataBase

    private lateinit var favoritesDao: FavoritesDao

    @Before
    fun initDb() {

        favoritesDatabase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            FavoritesDataBase::class.java
        ).build()
        favoritesDao = favoritesDatabase.favoritesDao()

    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        favoritesDatabase.close()
    }


    private fun populateDB() {
        runBlocking {
            favoritesDao.insertDetail(ResultDetail("123", timestamp = Date(0)))
            favoritesDao.insertTaxes(listOf(ResultDetailTaxes("123")))
            favoritesDao.insertGallery(listOf(ResultDetailGallery("123")))
            favoritesDao.insertAmenities(listOf(ResultDetailAmenities("123")))

            favoritesDao.insertDetail(ResultDetail("abc", timestamp = Date(100)))
            favoritesDao.insertTaxes(listOf(ResultDetailTaxes("abc")))
            favoritesDao.insertGallery(listOf(ResultDetailGallery("abc")))
            favoritesDao.insertAmenities(listOf(ResultDetailAmenities("abc")))
        }
    }


    @Test
    @Throws(Exception::class)
    fun insertFavorites() {

        populateDB()
        val favorites = favoritesDao.getAllDesc()
        favorites.observeForever {
            Assert.assertEquals(false, favorites.value.isNullOrEmpty())
        }

    }

    @Test
    @Throws(Exception::class)
    fun getFavorites() {

        populateDB()
        val favorites = favoritesDao.getAllDesc()
        favorites.observeForever {

            Assert.assertEquals(
                "abc",
                favorites.value?.firstOrNull()?.resultDetail?.sku
            )

            Assert.assertEquals(
                "abc",
                favorites.value?.firstOrNull()?.resultDetailTaxes?.firstOrNull()?.sku
            )

            Assert.assertEquals(
                "abc",
                favorites.value?.firstOrNull()?.resultDetailGallery?.firstOrNull()?.sku
            )

            Assert.assertEquals(
                "abc",
                favorites.value?.firstOrNull()?.resultDetailAmenities?.firstOrNull()?.sku
            )

        }

    }

    @Test
    @Throws(Exception::class)
    fun isFavorited() {

        populateDB()
        val favorites = favoritesDao.isFavorited("123")
        favorites.observeForever {
            Assert.assertEquals("123", favorites.value?.sku)
        }


    }

    @Test
    fun deleteFavorite() {

        populateDB()
        runBlocking {
            favoritesDao.deleteFavorited("123")
        }

        val favorites = favoritesDao.isFavorited("123")
        favorites.observeForever {
            Assert.assertEquals(null, favorites.value?.sku)
        }


    }

}

