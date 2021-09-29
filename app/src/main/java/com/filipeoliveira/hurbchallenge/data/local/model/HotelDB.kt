package com.filipeoliveira.hurbchallenge.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Collections.emptyList

@Entity
data class HotelDB(
    @PrimaryKey val id: String,
    val smallDescription: String,
    val amenities: String,
    val priceValue: String,
    val priceCurrency: String,
    val image: String,
    val name: String,
    val url: String,
    val description: String,
    val stars: Int,
    val images: String,
    val city: String,
    val state: String,
    val country: String,
    val street: String
) {
    fun getAmenitiesAsList() : List<String>{
        var list = emptyList<String>()

        val splitString = amenities.split(", ")

        for(amenity in splitString){
            list.add(amenity)
        }

        return list
    }

    fun getGalleryImagesURLAsList() : List<String>{
        var list = emptyList<String>()

        val splitString = image.split(", ")

        for(url in splitString){
            list.add(url)
        }

        return list
    }
}