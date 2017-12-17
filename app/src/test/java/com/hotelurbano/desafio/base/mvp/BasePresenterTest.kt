package com.hotelurbano.desafio.base.mvp

import com.hotelurbano.desafio.util.TestSchedulerProvider
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * Created by Cristian on 17/12/2017.
 */
class BasePresenterTest {

    private lateinit var basePresenter: BasePresenter<BaseView>
    private val view: BaseView = mock()

    @Before
    fun setUp() {
        val compositeDisposable = CompositeDisposable()
        val testSchedulerProvider = TestSchedulerProvider(TestScheduler())
        basePresenter = BasePresenter(compositeDisposable, testSchedulerProvider)
    }

    @Test
    fun attachView() {
        basePresenter.attachView(view)

        verify(view).setPresenter(basePresenter)
    }

    @After
    fun tearDown(){
        basePresenter.detachView()
    }
}