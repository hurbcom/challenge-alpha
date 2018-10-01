package com.belfortdev.hurbchallenge.core.model

object SearchDomain {

    data class Hotel(
            val id: String,
            val name: String?,
            val price: HotelPrice?,
            val address: Address?,
            val stars: Float?,
            val image: String?,
            val amenities: List<Amenity?>?,
            val sku: String?,
            val isPackage: Boolean?,
            val url: String?,
            val smallDescription: String?,
            val description: String?,
            val category: String?,
            val isHotel: Boolean?,
            val huFreeCancellation: Boolean?
    ) {
        constructor(hotelResponse: SearchResponse.Hotel) : this(
                id = hotelResponse.id,
                name = hotelResponse.name,
                price = HotelPrice(hotelResponse.price),
                address = Address(hotelResponse.address),
                stars = hotelResponse.stars,
                image = hotelResponse.image,
                amenities = hotelResponse.amenities?.map { it?.let { it1 -> Amenity(it1) } },
                sku = hotelResponse.sku,
                isPackage = hotelResponse.isPackage,
                url = hotelResponse.url,
                smallDescription = hotelResponse.smallDescription,
                description = hotelResponse.description,
                category = hotelResponse.category,
                isHotel = hotelResponse.isHotel,
                huFreeCancellation = hotelResponse.huFreeCancellation
        )

        constructor(hotelEntity: SearchEntity.Hotel) : this(
                id = hotelEntity.id,
                name = hotelEntity.name,
                price = HotelPrice(hotelEntity.price),
                address = Address(hotelEntity.address),
                stars = hotelEntity.stars,
                image = hotelEntity.image,
                amenities = hotelEntity.amenities?.map { it?.let { it1 -> Amenity(it1) } },
                sku = hotelEntity.sku,
                isPackage = hotelEntity.isPackage,
                url = hotelEntity.url,
                smallDescription = hotelEntity.smallDescription,
                description = hotelEntity.description,
                category = hotelEntity.category,
                isHotel = hotelEntity.isHotel,
                huFreeCancellation = hotelEntity.huFreeCancellation
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
        constructor(hotelPriceResponse: SearchResponse.HotelPrice?) : this(
                cost = hotelPriceResponse?.cost,
                costFee = hotelPriceResponse?.costFee,
                costPrice = hotelPriceResponse?.costPrice,
                currentPrice = hotelPriceResponse?.currentPrice,
                oldPrice = hotelPriceResponse?.oldPrice,
                originalAmountPerDay = hotelPriceResponse?.originalAmountPerDay,
                amountPerDay = hotelPriceResponse?.amountPerDay,
                amount = hotelPriceResponse?.amount
        )

        constructor(hotelPriceEntity: SearchEntity.HotelPrice?) : this(
                cost = hotelPriceEntity?.cost,
                costFee = hotelPriceEntity?.costFee,
                costPrice = hotelPriceEntity?.costPrice,
                currentPrice = hotelPriceEntity?.currentPrice,
                oldPrice = hotelPriceEntity?.oldPrice,
                originalAmountPerDay = hotelPriceEntity?.originalAmountPerDay,
                amountPerDay = hotelPriceEntity?.amountPerDay,
                amount = hotelPriceEntity?.amount
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
        constructor(addressResponse: SearchResponse.Address?) : this(
                city = addressResponse?.city,
                country = addressResponse?.country,
                idCity = addressResponse?.idCity,
                idCountry = addressResponse?.idCountry,
                idState = addressResponse?.idState,
                state = addressResponse?.state,
                street = addressResponse?.street,
                zipcode = addressResponse?.zipcode
        )

        constructor(addressEntity: SearchEntity.Address?) : this(
                city = addressEntity?.city,
                country = addressEntity?.country,
                idCity = addressEntity?.idCity,
                idCountry = addressEntity?.idCountry,
                idState = addressEntity?.idState,
                state = addressEntity?.state,
                street = addressEntity?.street,
                zipcode = addressEntity?.zipcode
        )

    }

    data class Amenity(
            val name: String?,
            val category: String?
    ) {
        constructor(amenityResponse: SearchResponse.Amenity?) : this(
                name = amenityResponse?.name,
                category = amenityResponse?.category
        )

        constructor(amenityEntity: SearchEntity.Amenity?) : this(
                name = amenityEntity?.name,
                category = amenityEntity?.category
        )
    }

}