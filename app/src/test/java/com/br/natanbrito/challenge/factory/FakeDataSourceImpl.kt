package com.br.natanbrito.challenge.factory

import com.br.natanbrito.challenge.data.datasource.HotelDataSource
import com.br.natanbrito.challenge.data.model.Hotel
import com.br.natanbrito.challenge.data.model.HotelNetworkResult
import com.br.natanbrito.challenge.data.model.results.Address
import com.br.natanbrito.challenge.data.model.results.AmenityResults
import com.br.natanbrito.challenge.data.model.results.FeaturedItem
import com.br.natanbrito.challenge.data.model.results.Gallery
import com.br.natanbrito.challenge.data.model.results.GeoLocation
import com.br.natanbrito.challenge.data.model.results.PriceResults
import com.br.natanbrito.challenge.data.model.results.QuantityDescriptors
import com.br.natanbrito.challenge.data.model.results.Result

object FakeDataSourceImpl : HotelDataSource {

    override suspend fun fetchHotels(): HotelNetworkResult.Success = HotelNetworkResult.Success(
        Hotel(
            null, listOf(
                Result(
                    address = Address(
                        "Salvador",
                        country = "Brasil",
                        geoLocation = GeoLocation(0.0, 0.0),
                        id_atlas_city = null,
                        id_atlas_country = null,
                        id_atlas_state = null,
                        id_atlas_neighborhood = null,
                        id_city = 0,
                        id_country = 0,
                        id_state = 0,
                        zipcode = "99999999",
                        state = "Bahia",
                        street = "Rua A"
                    ),
                    category = "hotel",
                    hu_free_cancellation = true,
                    isHotel = true,
                    description = "Hotel em Salvador na frente do mar",
                    smallDescription = "Hotel em Salvador",
                    id = "AT7987",
                    name = "Hotel Porto Salvador",
                    stars = 4,
                    url = "",
                    featuredItem = FeaturedItem(
                        amenities = listOf(
                            "banheiro",
                            "garagem",
                            "ar-condicionado"
                        ),
                        name = "Suite simples",
                        image = "",
                        description = "informações relevantes"
                    ),
                    image = "http://media.omnibees.com/Images/8821/Property/394671.jpg",
                    price = PriceResults(
                        amount = 160.0,
                        amountPerDay = 160.0,
                        currency = "BRL",
                        currency_original = "BRL",
                        sku = "",
                        originalAmountPerDay = 0.0,
                        current_price = 160.0,
                        old_price = 0.0
                    ),
                    sku = "",
                    quantityDescriptors = QuantityDescriptors(
                        maxAdults = 2,
                        maxChildren = 2,
                        maxFreeChildrenAge = 7
                    ),
                    tags = listOf("Salvador", "Nordeste", "Praia"),
                    amenities = listOf(AmenityResults("banheiro", "chuveiro quente")),
                    gallery = listOf(Gallery(description = "", url = ""))
                ),
                Result(
                    address = Address(
                        "Rio de Janeiro",
                        country = "Brasil",
                        geoLocation = GeoLocation(0.0, 0.0),
                        id_atlas_city = null,
                        id_atlas_country = null,
                        id_atlas_state = null,
                        id_atlas_neighborhood = null,
                        id_city = 0,
                        id_country = 0,
                        id_state = 0,
                        zipcode = "99999999",
                        state = "Rio de Janeiro",
                        street = "Rua B"
                    ),
                    category = "hotel",
                    hu_free_cancellation = true,
                    isHotel = true,
                    description = "Hotel em Salvador na frente do mar",
                    smallDescription = "Hotel em Salvador",
                    id = "AT7987",
                    name = "Hotel Porto Salvador",
                    stars = 4,
                    url = "",
                    featuredItem = FeaturedItem(
                        amenities = listOf(
                            "banheiro",
                            "garagem",
                            "ar-condicionado"
                        ),
                        name = "Suite simples",
                        image = "",
                        description = "informações relevantes"
                    ),
                    image = "http://media.omnibees.com/Images/8821/Property/394671.jpg",
                    price = PriceResults(
                        amount = 160.0,
                        amountPerDay = 160.0,
                        currency = "BRL",
                        currency_original = "BRL",
                        sku = "",
                        originalAmountPerDay = 0.0,
                        current_price = 160.0,
                        old_price = 0.0
                    ),
                    sku = "",
                    quantityDescriptors = QuantityDescriptors(
                        maxAdults = 2,
                        maxChildren = 2,
                        maxFreeChildrenAge = 7
                    ),
                    tags = listOf("Salvador", "Nordeste", "Praia"),
                    amenities = listOf(AmenityResults("banheiro", "chuveiro quente")),
                    gallery = listOf(Gallery(description = "", url = ""))
                ),
                Result(
                    address = Address(
                        "Guarujá",
                        country = "Brasil",
                        geoLocation = GeoLocation(0.0, 0.0),
                        id_atlas_city = null,
                        id_atlas_country = null,
                        id_atlas_state = null,
                        id_atlas_neighborhood = null,
                        id_city = 0,
                        id_country = 0,
                        id_state = 0,
                        zipcode = "99999999",
                        state = "São Paulco",
                        street = "Rua C"
                    ),
                    category = "hotel",
                    hu_free_cancellation = true,
                    isHotel = true,
                    description = "Hotel em Salvador na frente do mar",
                    smallDescription = "Hotel em Salvador",
                    id = "AT7987",
                    name = "Hotel Porto Salvador",
                    stars = 4,
                    url = "",
                    featuredItem = FeaturedItem(
                        amenities = listOf(
                            "banheiro",
                            "garagem",
                            "ar-condicionado"
                        ),
                        name = "Suite simples",
                        image = "",
                        description = "informações relevantes"
                    ),
                    image = "http://media.omnibees.com/Images/8821/Property/394671.jpg",
                    price = PriceResults(
                        amount = 160.0,
                        amountPerDay = 160.0,
                        currency = "BRL",
                        currency_original = "BRL",
                        sku = "",
                        originalAmountPerDay = 0.0,
                        current_price = 160.0,
                        old_price = 0.0
                    ),
                    sku = "",
                    quantityDescriptors = QuantityDescriptors(
                        maxAdults = 2,
                        maxChildren = 2,
                        maxFreeChildrenAge = 7
                    ),
                    tags = listOf("Salvador", "Nordeste", "Praia"),
                    amenities = listOf(AmenityResults("banheiro", "chuveiro quente")),
                    gallery = listOf(Gallery(description = "", url = ""))
                )
            )
        )
    )
}