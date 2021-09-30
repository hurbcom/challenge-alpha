package com.filipeoliveira.hurbchallenge.data

import com.filipeoliveira.hurbchallenge.data.local.HotelLocalDataSource
import com.filipeoliveira.hurbchallenge.data.local.model.HotelDB
import com.filipeoliveira.hurbchallenge.data.remote.HotelRemoteDataSource
import com.filipeoliveira.hurbchallenge.data.remote.model.AddressResponse
import com.filipeoliveira.hurbchallenge.data.remote.model.AmenityResponse
import com.filipeoliveira.hurbchallenge.data.remote.model.ImageResponse
import com.filipeoliveira.hurbchallenge.data.remote.model.QuantityDescriptorsResponse
import com.filipeoliveira.hurbchallenge.ui.model.*
import java.util.Collections.emptyList

class HotelRepositoryImpl(
    val remoteDataSource: HotelRemoteDataSource,
    val localDataSource: HotelLocalDataSource
) : HotelRepository {

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

    override fun getFavoriteHotels(): List<HotelUI> {
        val hotelList = localDataSource.getFavoriteHotels().map {
            HotelUI(
                id = it.id,
                smallDescription = it.smallDescription,
                amenities = getAmenityUIFromDB(it),
                priceCurrency = PriceUI(
                    currency = it.priceCurrency,
                    pricePerDay = it.priceValue
                ),
                huFreeCancellation = true,
                image = it.image,
                name = it.name,
                url = it.url,
                description = it.description,
                stars = it.stars,
                images = getImagesUIFromDB(it),
                tags = emptyList(),
                quantityDescriptors = QuantityDescriptorsUI(0, 0, 0),
                address = AddressUI(
                    state = it.state,
                    street = it.street,
                    country = it.country,
                    city = it.city
                )
            )
        }

        return hotelList
    }

    private fun getImagesUIFromDB(it: HotelDB): List<ImageUI> {
        val imageUIList = mutableListOf<ImageUI>()

        val imagesFromDB = it.images.split(",")


        for (imageURL in imagesFromDB) {
            imageUIList.add(
                ImageUI(
                    url = imageURL,
                    description = ""
                )
            )
        }

        return imageUIList
    }

    private fun getAmenityUIFromDB(it: HotelDB): List<AmenityUI> {
        val amenitiesList = mutableListOf<AmenityUI>()
        val amenitiesFromDB = it.getAmenitiesAsList()

        for (amenity in amenitiesFromDB) {
            amenitiesList.add(
                AmenityUI(
                    name = amenity
                )
            )
        }

        return amenitiesList
    }

    override fun addToFavorites(hotelUI: HotelUI) {
        localDataSource.addToFavorites(
            HotelDB(
                id = hotelUI.id,
                amenities = hotelUI.getAmenitiesAsString(),
                priceCurrency = hotelUI.priceCurrency.currency,
                priceValue = hotelUI.priceCurrency.pricePerDay,
                image = hotelUI.image,
                name = hotelUI.name,
                url = hotelUI.url,
                description = hotelUI.description,
                stars = hotelUI.stars,
                images = hotelUI.getGalleryImagesURLAsString(),
                city = hotelUI.address.city,
                street = hotelUI.address.street,
                state = hotelUI.address.state,
                country = hotelUI.address.country,
                smallDescription = hotelUI.smallDescription
            )
        )
    }

    override fun removeFromFavorites(hotelUI: HotelUI) {
        localDataSource.removeFromFavorites(hotelUI.id)
    }

    override fun isFavorite(hotelUI: HotelUI): Boolean {
        return localDataSource.isFavorite(hotelUI.id)
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
        return if (quantityDescriptors == null) {
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