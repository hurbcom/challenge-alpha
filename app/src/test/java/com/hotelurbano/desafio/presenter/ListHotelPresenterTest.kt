package com.hotelurbano.desafio.presenter

import com.hotelurbano.desafio.api.Endpoints
import com.hotelurbano.desafio.listhotels.model.HotelItem
import com.hotelurbano.desafio.listhotels.model.HotelResponse
import com.hotelurbano.desafio.listhotels.presenter.ListHotelPresenter
import com.hotelurbano.desafio.listhotels.view.ListHotelView
import com.hotelurbano.desafio.util.TestSchedulerProvider
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

/**
 * Created by Cristian on 17/12/2017.
 */
class ListHotelPresenterTest {

    private val view: ListHotelView = mock()
    private val api: Endpoints = mock()
    private lateinit var presenter: ListHotelPresenter
    private lateinit var testScheduler: TestScheduler
    private val city = "Rio de Janeiro"

    @Before
    fun setup() {
        val compositeDisposable = CompositeDisposable()
        testScheduler = TestScheduler()
        val testSchedulerProvider = TestSchedulerProvider(testScheduler)
        presenter = ListHotelPresenter(api, compositeDisposable, testSchedulerProvider)
        presenter.attachView(view)
    }

    @Test
    fun test_getHotels_should_callSuccess() {
        val mockedResponse: HotelResponse = mock()

        doReturn(Observable.just(mockedResponse))
            .`when`(api)
            .getHotels(city)

        presenter.getHotels(city)

        testScheduler.triggerActions()

        verify(view).showProgress()
        verify(view).onResponse(mockedResponse.results)
        verify(view).hideProgress()
    }

    @Test
    fun test_getHotels_shouldNot_callNoResult() {
        val mockedResponse: HotelResponse = mock()
        val items = ArrayList<HotelItem>()

        items.add(Mockito.mock(HotelItem::class.java))

        Mockito.`when`(mockedResponse.results).thenReturn(items)

        doReturn(Observable.just(mockedResponse))
            .`when`(api)
            .getHotels(city)

        presenter.getHotels(city)

        testScheduler.triggerActions()

        verify(view, Mockito.times(0)).noResult()
    }

    @Test
    fun test_getHotels_should_callNoResult() {
        val mockedResponse: HotelResponse = mock()
        val items = ArrayList<HotelItem>()

        Mockito.`when`(mockedResponse.results).thenReturn(items)

        doReturn(Observable.just(mockedResponse))
            .`when`(api)
            .getHotels(city)

        presenter.getHotels(city)

        testScheduler.triggerActions()

        verify(view).noResult()
    }

    @Test
    fun test_getHotels_should_callError() {
        val mockedResponse: Throwable = mock()

        doReturn(Observable.just(mockedResponse))
            .`when`(api)
            .getHotels(city)

        presenter.getHotels(city)

        testScheduler.triggerActions()

        verify(view).showProgress()
        verify(view).onError()
        verify(view).hideProgress()
    }

    @After
    fun tearDown() {
        presenter.detachView()
    }
}