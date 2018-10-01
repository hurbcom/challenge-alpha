package com.belfortdev.hurbchallenge.list.data

import com.belfortdev.hurbchallenge.core.data.remote.SearchService
import com.belfortdev.hurbchallenge.core.model.SearchDomain
import io.reactivex.Single

class ListRemoteData(private val searchService: SearchService) : ListDataContract.Remote {

    override fun getHotels(): Single<List<SearchDomain.Hotel?>?> = searchService.getAllHotelsFromRio()
            .map { fullResponse ->
                fullResponse.results?.map { hotelResponse ->
                    hotelResponse?.let { it1 -> SearchDomain.Hotel(it1) }
                }
            }

}