package com.belfortdev.hurbchallenge.core.model

import android.arch.persistence.room.*
import com.belfortdev.hurbchallenge.core.data.Converter

object SearchEntity {

    @Entity(indices = [(Index("id"))])
    data class Hotel(
            @PrimaryKey val id: String,
            val name: String?,
            @Embedded val price: HotelPrice?,
            @Embedded val address: Address?,
            val stars: Float?,
            val image: String?,
            @TypeConverters(Converter::class) val amenities: List<Amenity?>?,
            val sku: String?,
            val isPackage: Boolean?,
            val url: String?,
            val smallDescription: String?,
            val description: String?,
            val category: String?,
            val isHotel: Boolean?,
            val huFreeCancellation: Boolean?
    ) {
        constructor(hotel: SearchDomain.Hotel) : this(
                id = hotel.id,
                name = hotel.name,
                price = HotelPrice(hotel.price),
                address = Address(hotel.address),
                stars = hotel.stars,
                image = hotel.image,
                amenities = hotel.amenities?.map { it?.let { it1 -> Amenity(it1) } },
                sku = hotel.sku,
                isPackage = hotel.isPackage,
                url = hotel.url,
                smallDescription = hotel.smallDescription,
                description = hotel.description,
                category = hotel.category,
                isHotel = hotel.isHotel,
                huFreeCancellation = hotel.huFreeCancellation
        )
    }

    data class HotelPrice(
            val cost: Double?,
            val costFee: Double?,
            val costPrice: Double?,
            val currentPrice: Double?,
            val oldPrice: Double?,
            val originalAmountPerDay: Double?,
            val amountPerDay: Double?,
            val amount: Double?
    ) {
        constructor(hotelPrice: SearchDomain.HotelPrice?) : this(
                cost = hotelPrice?.cost,
                costFee = hotelPrice?.costFee,
                costPrice = hotelPrice?.costPrice,
                currentPrice = hotelPrice?.currentPrice,
                oldPrice = hotelPrice?.oldPrice,
                originalAmountPerDay = hotelPrice?.originalAmountPerDay,
                amountPerDay = hotelPrice?.amountPerDay,
                amount = hotelPrice?.amount
        )

    }

    data class Address(
            val city: String?,
            val country: String?,
            val idCity: Int?,
            val idCountry: Int?,
            val idState: Int?,
            val state: String?,
            val street: String?,
            val zipcode: String?
    ) {
        constructor(address: SearchDomain.Address?) : this(
                city = address?.city,
                country = address?.country,
                idCity = address?.idCity,
                idCountry = address?.idCountry,
                idState = address?.idState,
                state = address?.state,
                street = address?.street,
                zipcode = address?.zipcode
        )
    }

    data class Amenity(
            val name: String?,
            val category: String?
    ) {
        constructor(amenity: SearchDomain.Amenity?) : this(
                name = amenity?.name,
                category = amenity?.category
        )
    }

}