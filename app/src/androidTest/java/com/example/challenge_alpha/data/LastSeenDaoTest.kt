package com.example.challenge_alpha.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.challenge_alpha.data.lastSeen.LastSeenDao
import com.example.challenge_alpha.data.lastSeen.LastSeenDataBase
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
class LastSeenDaoTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()


    private lateinit var lastSeenDataBase: LastSeenDataBase

    private lateinit var lastSeenDao: LastSeenDao

    @Before
    fun initDb() {

        lastSeenDataBase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            LastSeenDataBase::class.java
        ).build()
        lastSeenDao = lastSeenDataBase.lastSeenDao()

    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        lastSeenDataBase.close()
    }

    private fun populateDB() {
        runBlocking {
            lastSeenDao.insertDetail(ResultDetail("123", timestamp = Date(0)))
            lastSeenDao.insertTaxes(listOf(ResultDetailTaxes("123")))
            lastSeenDao.insertGallery(listOf(ResultDetailGallery("123")))
            lastSeenDao.insertAmenities(listOf(ResultDetailAmenities("123")))

            lastSeenDao.insertDetail(ResultDetail("abc", timestamp = Date(100)))
            lastSeenDao.insertTaxes(listOf(ResultDetailTaxes("abc")))
            lastSeenDao.insertGallery(listOf(ResultDetailGallery("abc")))
            lastSeenDao.insertAmenities(listOf(ResultDetailAmenities("abc")))
        }
    }

    @Test
    @Throws(Exception::class)
    fun insertLastSeen() {

        populateDB()
        val lastSeen = lastSeenDao.getAllDesc()
        lastSeen.observeForever {
            Assert.assertEquals(false, lastSeen.value.isNullOrEmpty())
        }

    }

    @Test
    @Throws(Exception::class)
    fun getLastSeen() {

        populateDB()
        val lastSeen = lastSeenDao.getAllDesc()
        lastSeen.observeForever {

            Assert.assertEquals(
                "abc",
                lastSeen.value?.firstOrNull()?.resultDetail?.sku
            )

            Assert.assertEquals(
                "abc",
                lastSeen.value?.firstOrNull()?.resultDetailTaxes?.firstOrNull()?.sku
            )

            Assert.assertEquals(
                "abc",
                lastSeen.value?.firstOrNull()?.resultDetailGallery?.firstOrNull()?.sku
            )

            Assert.assertEquals(
                "abc",
                lastSeen.value?.firstOrNull()?.resultDetailAmenities?.firstOrNull()?.sku
            )

        }

    }


    @Test
    @Throws(Exception::class)
    fun getRandom() {

        populateDB()
        val lastSeen = lastSeenDao.getAllRandom()
        lastSeen.observeForever {

            Assert.assertEquals(
                2,
                lastSeen.value?.size
            )

        }



    }


}
