package com.filipeoliveira.hurbchallenge.data

import com.filipeoliveira.hurbchallenge.data.local.HotelLocalDataSource
import com.filipeoliveira.hurbchallenge.data.local.model.HotelDB
import com.filipeoliveira.hurbchallenge.data.remote.HotelRemoteDataSource
import com.filipeoliveira.hurbchallenge.data.remote.model.FilterResponse
import com.filipeoliveira.hurbchallenge.data.remote.model.HotelResponse
import com.filipeoliveira.hurbchallenge.data.remote.model.HotelsInfoResponse
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.Collections.emptyList

class HotelRepositoryImplTest {

    private lateinit var repositoryImpl: HotelRepositoryImpl

    @Before
    fun setupRepository() {
        repositoryImpl = HotelRepositoryImpl(
            remoteDataSource = FakeHotelRemoteDataSource(),
            localDataSource = FakeHotelLocalDataSource()
        )
    }

    @Test
    fun nullSmallDescriptionShouldReturnEmptyString() {
        val hotelUI = repositoryImpl.getHotelList("", emptyList()).hotelList.first()

        assertEquals("", hotelUI.smallDescription)
    }

    @Test
    fun nullAmenityListShouldReturnEmptyList() {
        val hotelUI = repositoryImpl.getHotelList("", emptyList()).hotelList.first()

        assertEquals(true, hotelUI.amenities.isEmpty())
    }

    @Test
    fun nullPriceAndCurrencyShouldReturnEmptyString() {
        val hotelUI = repositoryImpl.getHotelList("", emptyList()).hotelList.first()

        assertEquals("", hotelUI.priceCurrency.currency)
        assertEquals("", hotelUI.priceCurrency.pricePerDay)
    }

    @Test
    fun nullImageShouldReturnEmptyString() {
        val hotelUI = repositoryImpl.getHotelList("", emptyList()).hotelList.first()

        assertEquals("", hotelUI.image)
    }

    @Test
    fun nullNameShouldReturnEmptyString() {
        val hotelUI = repositoryImpl.getHotelList("", emptyList()).hotelList.first()

        assertEquals("", hotelUI.name)
    }

    @Test
    fun nullUrlShouldReturnEmptyString() {
        val hotelUI = repositoryImpl.getHotelList("", emptyList()).hotelList.first()

        assertEquals("", hotelUI.url)
    }

    @Test
    fun nullDescriptionShouldReturnEmptyString() {
        val hotelUI = repositoryImpl.getHotelList("", emptyList()).hotelList.first()

        assertEquals("", hotelUI.description)
    }

    @Test
    fun nullImagesShouldReturnImageUIWithEmptyList() {
        val hotelUI = repositoryImpl.getHotelList("", emptyList()).hotelList.first()

        assertEquals(true, hotelUI.images.isEmpty())
    }

    @Test
    fun nullAddressShouldReturnEmptyAddressUI() {
        val hotelUI = repositoryImpl.getHotelList("", emptyList()).hotelList.first()

        assertEquals("", hotelUI.address.city)
        assertEquals("", hotelUI.address.state)
        assertEquals("", hotelUI.address.street)
        assertEquals("", hotelUI.address.country)
    }

    @Test
    fun amenitiesFromDBShouldBeParsedCorrectly() {
        val hotelDB = repositoryImpl.localDataSource.getFavoriteHotels().first()

        assertEquals(true, hotelDB.getAmenitiesAsList().contains("Bar"))
        assertEquals(true, hotelDB.getAmenitiesAsList().contains("Elevador"))
        assertEquals(true, hotelDB.getAmenitiesAsList().contains("TV"))
    }

    @Test
    fun imagesFromDBShouldBeParsedCorrectly() {
        val hotelDB = repositoryImpl.localDataSource.getFavoriteHotels().first()

        assertEquals(true, hotelDB.getGalleryImagesURLAsList().contains("www.foto1.com.br"))
        assertEquals(true, hotelDB.getGalleryImagesURLAsList().contains("www.foto2.com.br"))
        assertEquals(true, hotelDB.getGalleryImagesURLAsList().contains("www.foto3.com.br"))
    }
}

class FakeHotelRemoteDataSource() : HotelRemoteDataSource {
    override fun getHotelList(query: String, enabledFilters: List<String>): HotelsInfoResponse {
        val hotelResponse = HotelResponse(
            id = "id",
            smallDescription = null,
            amenities = null,
            price = null,
            huFreeCancellation = null,
            image = null,
            name = null,
            url = null,
            description = null,
            starts = 0,
            images = emptyList(),
            address = null,
            quantityDescriptors = null,
            tags = null
        )

        val hotelListResponse = listOf(
            hotelResponse
        )

        val filters = FilterResponse(amenities = null)
        return HotelsInfoResponse(result = hotelListResponse, filters = filters)
    }
}

class FakeHotelLocalDataSource() : HotelLocalDataSource {
    override fun getFavoriteHotels(): List<HotelDB> {
        return listOf(
            HotelDB(
                id = "id",
                smallDescription = "",
                amenities = "Bar,Elevador,TV",
                priceValue = "250",
                priceCurrency = "BRL",
                image = "www.foto.com.br",
                name = "nome",
                url = "",
                description = "",
                stars = 1,
                city = "",
                images = "www.foto1.com.br,www.foto2.com.br,www.foto3.com.br",
                state = "",
                country = "",
                street = ""
            )
        )
    }

    override fun addToFavorites(hotel: HotelDB) {

    }

    override fun removeFromFavorites(hotel: String) {

    }

    override fun isFavorite(hotel: String): Boolean {
        return true
    }

}