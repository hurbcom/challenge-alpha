package com.example.challenge_alpha.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.challenge_alpha.data.resultDetail.ResultDetailDao
import com.example.challenge_alpha.data.resultDetail.ResultDetailDataBase
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
class ResultDetailDaoTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()


    private lateinit var resultDetailDataBase: ResultDetailDataBase

    private lateinit var resultDetailDao: ResultDetailDao

    @Before
    fun initDb() {

        resultDetailDataBase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            ResultDetailDataBase::class.java
        ).build()
        resultDetailDao = resultDetailDataBase.resultDetailDao()

    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        resultDetailDataBase.close()
    }

    private fun populateDB() {
        runBlocking {
            resultDetailDao.insertDetail(ResultDetail("123", timestamp = Date(0)))
            resultDetailDao.insertTaxes(listOf(ResultDetailTaxes("123")))
            resultDetailDao.insertGallery(listOf(ResultDetailGallery("123")))
            resultDetailDao.insertAmenities(listOf(ResultDetailAmenities("123")))

            resultDetailDao.insertDetail(ResultDetail("abc", timestamp = Date(100)))
            resultDetailDao.insertTaxes(listOf(ResultDetailTaxes("abc")))
            resultDetailDao.insertGallery(listOf(ResultDetailGallery("abc")))
            resultDetailDao.insertAmenities(listOf(ResultDetailAmenities("abc")))
        }
    }

    private fun populateRandom() {
        runBlocking {
            for (i in 1..20) {
                resultDetailDao.insertDetail(ResultDetail(i.toString(), timestamp = Date(0)))
            }
        }
    }
    

    @Test
    @Throws(Exception::class)
    fun insertFavorites() {

        populateDB()
        val result = resultDetailDao.getAllDesc()
        result.observeForever {
            Assert.assertEquals(false, result.value.isNullOrEmpty())
        }

    }

    @Test
    @Throws(Exception::class)
    fun getResult() {

        populateDB()
        val result = resultDetailDao.getResult("123")
        result.observeForever {

            Assert.assertEquals(
                "123",
                result.value?.resultDetail?.sku
            )

            Assert.assertEquals(
                "123",
                result.value?.resultDetailTaxes?.firstOrNull()?.sku
            )

            Assert.assertEquals(
                "123",
                result.value?.resultDetailGallery?.firstOrNull()?.sku
            )

            Assert.assertEquals(
                "123",
                result.value?.resultDetailAmenities?.firstOrNull()?.sku
            )

        }

    }

    @Test
    @Throws(Exception::class)
    fun isFavorited() {

        populateDB()
        runBlocking {
            val result = resultDetailDao.isFavorited("123")
            Assert.assertEquals("123", result)
        }

    }

    @Test
    fun deleteFavorite() {

        populateDB()
        runBlocking {
            resultDetailDao.deleteFavorited("123")
        }

        val favorites = resultDetailDao.getResult("123")
        favorites.observeForever {
            Assert.assertEquals(null, favorites.value?.resultDetail?.sku)
        }


    }

    @Test
    @Throws(Exception::class)
    fun getLastSeen() {

        populateDB()
        val result = resultDetailDao.getAllDesc()
        result.observeForever {

            Assert.assertEquals(
                "abc",
                result.value?.firstOrNull()?.resultDetail?.sku
            )

            Assert.assertEquals(
                "abc",
                result.value?.firstOrNull()?.resultDetailTaxes?.firstOrNull()?.sku
            )

            Assert.assertEquals(
                "abc",
                result.value?.firstOrNull()?.resultDetailGallery?.firstOrNull()?.sku
            )

            Assert.assertEquals(
                "abc",
                result.value?.firstOrNull()?.resultDetailAmenities?.firstOrNull()?.sku
            )

        }

    }


    @Test
    @Throws(Exception::class)
    fun getRandom() {

        populateRandom()
        
        val lastSeen = resultDetailDao.getAllRandom()
        lastSeen.observeForever {

            Assert.assertEquals(
                15,
                lastSeen.value?.size
            )

        }



    }


}