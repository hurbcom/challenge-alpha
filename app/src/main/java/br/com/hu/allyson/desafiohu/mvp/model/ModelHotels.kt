package br.com.hu.allyson.desafiohu.mvp.model

import br.com.hu.allyson.desafiohu.domain.Result
import br.com.hu.allyson.desafiohu.mvp.interfaces.APIHotels
import br.com.hu.allyson.desafiohu.mvp.presenter.PresenterHotels
import br.com.hu.allyson.desafiohu.network.NetworkHotels
import retrofit2.Call
import retrofit2.Response

class ModelHotels : APIHotels.ModelHotelsImpl {

    var repository: NetworkHotels.HotelsRepositoryImpl
    var presenter: PresenterHotels


    constructor(repository: NetworkHotels.HotelsRepositoryImpl,
                presenter: PresenterHotels) {
        this.presenter = presenter
        this.repository = repository
    }

    override fun requestHotels() {
        this.repository.request(object : retrofit2.Callback<Result> {
            override fun onFailure(call: Call<Result>, t: Throwable) {
                presenter.requestHotelsError()
            }

            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                if (response.isSuccessful) {
                    presenter.requestHotelsSucess(response.body()!!)
                } else {
                    presenter.requestHotelsError()
                }
            }

        })
    }
}