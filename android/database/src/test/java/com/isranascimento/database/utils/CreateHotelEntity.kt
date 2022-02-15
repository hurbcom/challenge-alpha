package com.isranascimento.database.utils

import com.isranascimento.databasedtos.hotels.HotelDatabaseEntity

fun createHotelEntity(number: Int, insertedTime: Long? = null) = HotelDatabaseEntity(
    id = number.toString(),
    insertedTime = insertedTime ?: number.toLong(),
    city = "City $number",
    state = "State $number",
    mainImage = "mainImage $number",
    name = "Name $number",
    starCount = number,
    description = "Description $number",
    url = "Url $number"
)
