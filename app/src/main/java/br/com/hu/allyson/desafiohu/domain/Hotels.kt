package br.com.hu.allyson.desafiohu.domain

import android.os.Parcel
import android.os.Parcelable

data class Hotels(
    val sku: String,
    val isHotel: Boolean,
    val isPackage: Boolean,
    val amenities: List<Amenity>,
    val smallDescription: String,
    val id: String,
    val price: Price,
    val image: String,
    val name: String,
    val stars: Int,
    val address: Address,
    val gallery: List<Gallery>
)