package com.filipeoliveira.hurbchallenge.data

import com.filipeoliveira.hurbchallenge.data.remote.HotelDataSource
import com.filipeoliveira.hurbchallenge.data.remote.model.AddressResponse
import com.filipeoliveira.hurbchallenge.data.remote.model.AmenityResponse
import com.filipeoliveira.hurbchallenge.data.remote.model.ImageResponse
import com.filipeoliveira.hurbchallenge.data.remote.model.QuantityDescriptorsResponse
import com.filipeoliveira.hurbchallenge.ui.model.*
import java.util.Collections.emptyList

class HotelRepositoryImpl(
    val remoteDataSource: HotelDataSource
) : HotelRepository{

    override fun getHotelList(query: String, enabledFilters: List<String>): HotelInfoUI {
        val response = remoteDataSource.getHotelList(query, enabledFilters)
        val hotelList = response.result.map {
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

        val filterUI = FilterUI(
            amenities = response.filters.amenities?.map {
                AmenityUI(
                    name = it.name,
                    filter = it.filter
                )
            } ?: emptyList()
        )

        return HotelInfoUI(filters = filterUI, hotelList = hotelList)
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

    private fun getAmenitiesUIListFromResponse(amenities: List<AmenityResponse>?): List<AmenityUI> {
        if (amenities.isNullOrEmpty()) return emptyList()

        return amenities.map {
            AmenityUI(
                name = it.name
            )
        }
    }

}