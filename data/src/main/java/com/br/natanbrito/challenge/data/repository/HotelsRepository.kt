package com.br.natanbrito.challenge.data.repository

import com.br.natanbrito.challenge.data.model.Hotel


interface HotelsRepository {

    suspend fun getHotels(): Hotel?

}