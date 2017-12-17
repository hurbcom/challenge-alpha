package com.hotelurbano.desafio.base.mvp

/**
 * Created by Cristian on 17/12/2017.
 */
interface Presenter<in V : BaseView> {
    fun attachView(view: V)
    fun detachView()
}