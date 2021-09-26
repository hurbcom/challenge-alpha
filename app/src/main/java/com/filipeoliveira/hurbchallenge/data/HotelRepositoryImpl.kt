package com.filipeoliveira.hurbchallenge.data

import com.filipeoliveira.hurbchallenge.data.remote.HotelDataSource
import com.filipeoliveira.hurbchallenge.data.remote.model.AddressResponse
import com.filipeoliveira.hurbchallenge.data.remote.model.AmenitiesResponse
import com.filipeoliveira.hurbchallenge.data.remote.model.ImageResponse
import com.filipeoliveira.hurbchallenge.data.remote.model.QuantityDescriptorsResponse
import com.filipeoliveira.hurbchallenge.ui.model.*

class HotelRepositoryImpl(
    val remoteDataSource: HotelDataSource
) : HotelRepository{

    override fun getHotelList(): List<HotelUI> {
        val hotelList = remoteDataSource.getHotelList().map {
            HotelUI(
                id = it.id ?: "",
                smallDescription = it.smallDescription ?: "",
                amenities = getAmenitiesUIListFromResponse(it.amenities),
                priceCurrency = PriceUI(
                    currency = it.price?.currency ?: "",
                    pricePerDay = it.price?.amountPerDay ?: ""
                ),
                huFreeCancellation = it.huFreeCancellation ?: false,
                image = it.image ?: "",
                name = it.name ?: "",
                url = it.url ?: "",
                description = it.description ?: "",
                stars = it.starts,
                images = getImagesUIFromResponse(it.images),
                tags = it.tags ?: emptyList(),
                quantityDescriptors = getQuantityDescriptionFromResponse(it.quantityDescriptors),
                address = getAddressUIFromResponse(it.address)
            )
        }

        return hotelList
    }

    private fun getAddressUIFromResponse(address: AddressResponse?): AddressUI {
        return AddressUI(
            city = address?.city ?: "",
            street = address?.state ?: "",
            state = address?.state ?: "",
            country = address?.country ?: ""
        )
    }

    private fun getQuantityDescriptionFromResponse(quantityDescriptors: QuantityDescriptorsResponse?): QuantityDescriptorsUI {
        return if (quantityDescriptors == null){
            QuantityDescriptorsUI(
                maxChildren = 0,
                maxFreeChildrenAge = 0,
                maxAdults = 0
            )
        } else {
            QuantityDescriptorsUI(
                maxChildren = quantityDescriptors.maxChildren ?: 0,
                maxFreeChildrenAge = quantityDescriptors.maxFreeChildrenAge ?: 0,
                maxAdults = quantityDescriptors.maxAdults ?: 0
            )
        }
    }

    private fun getImagesUIFromResponse(images: List<ImageResponse>): List<ImageUI> {
        if (images.isNullOrEmpty()) return emptyList()

        return images.map {
            ImageUI(
                url = it.url ?: "",
                description = it.description ?: ""
            )
        }
    }

    private fun getAmenitiesUIListFromResponse(amenities: List<AmenitiesResponse>?): List<AmenityUI> {
        if (amenities.isNullOrEmpty()) return emptyList()

        return amenities.map {
            AmenityUI(
                name = it.name
            )
        }
    }

}