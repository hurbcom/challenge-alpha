package com.filipeoliveira.hurbchallenge.data.remote

import com.filipeoliveira.hurbchallenge.data.remote.model.HotelResponse

interface HotelDataSource {
    fun getHotelList() : List<HotelResponse>
}
