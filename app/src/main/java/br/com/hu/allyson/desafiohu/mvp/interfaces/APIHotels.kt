package br.com.hu.allyson.desafiohu.mvp.interfaces

import br.com.hu.allyson.desafiohu.domain.Hotels
import br.com.hu.allyson.desafiohu.domain.Result
import br.com.hu.allyson.desafiohu.network.NetworkHotels

interface APIHotels {

    interface PresenterHotelsImpl{
        fun bind(view: ViewHotelsImpl, repository: NetworkHotels.HotelsRepositoryImpl)
        fun requestHotels()
        fun requestHotelsStart()
        fun requestHotelsSucess(result: Result)
        fun requestHotelsError()
    }

    interface ModelHotelsImpl{
        fun requestHotels()
    }

    interface ViewHotelsImpl{
        fun requestHotelsStart()
        fun requestHotelsSucess(result: Result)
        fun requestHotelsError()
    }


}