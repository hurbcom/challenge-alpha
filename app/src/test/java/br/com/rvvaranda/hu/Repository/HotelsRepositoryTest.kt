package br.com.rvvaranda.hu.Repository

import org.junit.Test

import org.junit.Assert.*

class HotelsRepositoryTest {

    @Test
    fun getHotels() {
        HotelsRepository().getHotels(1){success, payload ->
            assertEquals(success, true)
        }
    }
}