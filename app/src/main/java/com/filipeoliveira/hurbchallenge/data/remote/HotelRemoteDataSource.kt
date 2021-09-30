package com.filipeoliveira.hurbchallenge.data.remote

import com.filipeoliveira.hurbchallenge.data.remote.model.HotelsInfoResponse

interface HotelRemoteDataSource {
    fun getHotelList(query: String, enabledFilters: List<String>): HotelsInfoResponse
}
