package br.com.flyingdutchman.challenge_alpha.data

import br.com.flyingdutchman.challenge_alpha.data.network.HurbApi
import br.com.flyingdutchman.challenge_alpha.data.network.ResultRemoteEntityMapper
import io.reactivex.Scheduler
import io.reactivex.Single

class HurbRepository(
    private val api: HurbApi,
    private val resultMapper: ResultRemoteEntityMapper,
    private val ioScheduler: Scheduler

) {

    fun getHotels(): Single<List<ResultData>> {
        return api
            .fetchHotels(filters = "is_hotel|")
            .subscribeOn(ioScheduler)
            .map {
                resultMapper.mapFromRemote(it.results)
            }
    }

    fun getPackages(): Single<List<ResultData>> {
        return api
            .fetchHotels(filters = "is_offer|")
            .subscribeOn(ioScheduler)
            .map {
                resultMapper.mapFromRemote(it.results)
            }
    }

}