package com.hotelurbano.desafio.base.mvp

/**
 * Created by Cristian on 17/12/2017.
 */
interface BaseView {
    fun onError()
    fun setPresenter(presenter: BasePresenter<*>)
}