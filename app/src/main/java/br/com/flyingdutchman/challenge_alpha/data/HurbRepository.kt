package br.com.flyingdutchman.challenge_alpha.data

import br.com.flyingdutchman.challenge_alpha.data.network.HurbApi
import br.com.flyingdutchman.challenge_alpha.data.network.ResultRemoteEntityMapper
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.functions.BiFunction

class HurbRepository(
    private val api: HurbApi,
    private val resultMapper: ResultRemoteEntityMapper,
    private val ioScheduler: Scheduler

) {

    fun getHotels(): Single<List<ResultData>> {
        return api
            .search(filters = "is_hotel|1")
            .subscribeOn(ioScheduler)
            .map { apiResponse ->
                resultMapper
                    .mapFromRemote(apiResponse.results)
                    .sortedByDescending {
                        it.rating
                    }
            }
    }

    fun getPackages(): Single<List<ResultData>> {
        return api
            .search(filters = "is_offer|1")
            .subscribeOn(ioScheduler)
            .map {
                resultMapper.mapFromRemote(it.results)
            }
    }


    fun getAllTypeGroupedResults(): Single<List<GroupedResultData>> {
        return getAllTypeSearchResults()
            .subscribeOn(ioScheduler)
            .map { list ->
                groupByRating(list)
            }
            .map { groups ->
                createGroupedResultData(groups)
            }
    }

    private fun createGroupedResultData(groups: Map<Int, List<ResultData>>): List<GroupedResultData> {
        return groups.map {
            GroupedResultData(
                it.key.toString(),
                it.value
            )
        }
    }

    private fun groupByRating(list: List<ResultData>): Map<Int, List<ResultData>> {
        return list.groupBy {
            it.rating
        }
    }

    private fun getAllTypeSearchResults(): Single<List<ResultData>> = Single.zip(
        getHotels(),
        getPackages(),
        BiFunction { hotels,
                     packages ->

            return@BiFunction concatHotelsAndPackages(hotels, packages)
        }
    )

    private fun concatHotelsAndPackages(
        hotels: List<ResultData>,
        packages: List<ResultData>
    ): MutableList<ResultData> {
        val result = mutableListOf<ResultData>()
        result.addAll(hotels)
        result.addAll(packages)
        return result
    }
}