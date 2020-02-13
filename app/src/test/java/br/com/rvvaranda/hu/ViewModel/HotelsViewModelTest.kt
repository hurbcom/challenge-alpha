package br.com.rvvaranda.hu.ViewModel

import br.com.rvvaranda.hu.Repository.HotelsRepository
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class HotelsViewModelTest {


    private val hotelsRepository = mock(HotelsRepository::class.java)
    private lateinit var hotelsViewModel: HotelsViewModel
    @Before
    fun setUp() {
        hotelsViewModel = HotelsViewModel()
    }


    @Test
    fun loadAllHotels() {
       verify(hotelsRepository).getHotels(1){
           success, payload ->
       }

        verifyNoMoreInteractions(hotelsRepository)

    }
}