package br.com.hu.allyson.desafiohu.mvp.presenter

import br.com.hu.allyson.desafiohu.IOUtils
import br.com.hu.allyson.desafiohu.domain.Result
import br.com.hu.allyson.desafiohu.mvp.interfaces.APIHotels
import br.com.hu.allyson.desafiohu.network.NetworkHotels
import com.google.gson.Gson
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class PresenterHotelsTest {

    @Mock
    lateinit var view: APIHotels.ViewHotelsImpl
    @Mock
    lateinit var repository: NetworkHotels.HotelsRepositoryImpl
    @Mock
    lateinit var result: Result
    var presenter = PresenterHotels()
    var json = ""


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter.bind(view, repository)
        json = IOUtils.readStringFromResourcePath("result_request.json")
    }

    @Test
    fun bind() {
        assertNotNull(presenter.view)
        assertNotNull(presenter.repository)
    }

    @Test
    fun requestHotelsSucess() {
        presenter.requestHotelsSucess(result)
        Mockito.verify(view, Mockito.times(1)).requestHotelsSucess(result)
    }

    @Test
    fun requestHotelsError() {
        presenter.requestHotelsError()
        Mockito.verify(view, Mockito.times(1)).requestHotelsError()
    }

    @Test
    fun buildListHotels() {
        val result = Gson().fromJson<Result>(json, Result::class.java)
        val list = presenter.buildListHotels(result)
        assertEquals(19, list.size)
        assertEquals(5, list[0].stars)
        assertEquals(3, list[18].stars)
    }

    @Test
    fun buildListPackages() {
        val result = Gson().fromJson<Result>(json, Result::class.java)
        val list = presenter.buildListPackage(result)
        assertEquals(1, list.size)
        assertEquals(0, list[0].stars)
    }
}