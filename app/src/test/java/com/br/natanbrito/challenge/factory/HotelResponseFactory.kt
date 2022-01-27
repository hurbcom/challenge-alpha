package com.br.natanbrito.challenge.factory

import com.br.natanbrito.challenge.data.model.Hotel
import com.br.natanbrito.challenge.data.model.results.Address
import com.br.natanbrito.challenge.data.model.results.AmenityResults
import com.br.natanbrito.challenge.data.model.results.FeaturedItem
import com.br.natanbrito.challenge.data.model.results.Gallery
import com.br.natanbrito.challenge.data.model.results.GeoLocation
import com.br.natanbrito.challenge.data.model.results.PriceResults
import com.br.natanbrito.challenge.data.model.results.QuantityDescriptors
import com.br.natanbrito.challenge.data.model.results.Result

object HotelResponseFactory {

    private const val COUNTRY = "Brasil"
    private const val ZIP_CODE = "999999999"

    fun createHotels(): Hotel = Hotel(
        null,
        listOf(
            buildResult(
                addressValue = buildAddress("Salvador", "Bahia", "Rua A"),
                descriptionValue = "Hotel em Salvador na frente do mar",
                smallDescriptionValue = "Hotel em Salvador",
                nameValue = "Hotel Porto Salvador",
                tagsValue = listOf("Salvador", "Nordeste", "Praia")
            ),
            buildResult(
                addressValue = buildAddress("Rio de Janeiro", "Rio de Janeiro", "Rua B"),
                descriptionValue = "Hotel no Rio na frente do mar",
                smallDescriptionValue = "Hotel no Rio de Janeiro",
                nameValue = "Hotel Copacabana Pallace",
                tagsValue = listOf("Rio", "Cidade Maravilhosa", "Praia")
            ),
            buildResult(
                addressValue = buildAddress("Guarujá", "São Paulo", "Rua C"),
                descriptionValue = "Hotel no Guarujá na frente do mar",
                smallDescriptionValue = "Hotel no litoral paulista",
                nameValue = "Hotel Guarujá Mar",
                tagsValue = listOf("Guarujá", "Litoral Paulista", "Praia")
            )
        )
    )

    private fun buildAddress(
        cityName: String,
        stateName: String,
        streetName: String
    ) = Address(
        city = cityName,
        country = COUNTRY,
        geoLocation = GeoLocation(0.0, 0.0),
        id_atlas_city = null,
        id_atlas_country = null,
        id_atlas_state = null,
        id_atlas_neighborhood = null,
        id_city = 0,
        id_country = 0,
        id_state = 0,
        zipcode = ZIP_CODE,
        state = stateName,
        street = streetName
    )

    private fun buildPriceResult() = PriceResults(
        amount = 160.0,
        amountPerDay = 160.0,
        currency = "BRL",
        currency_original = "BRL",
        sku = "",
        originalAmountPerDay = 0.0,
        current_price = 160.0,
        old_price = 0.0
    )

    private fun buildFeaturedItem() = FeaturedItem(
        amenities = listOf(
            "banheiro",
            "garagem",
            "ar-condicionado"
        ),
        name = "Suite simples",
        image = "",
        description = "informações relevantes"
    )

    private fun buildResult(
        addressValue: Address,
        descriptionValue: String,
        smallDescriptionValue:
            String,
        nameValue: String,
        tagsValue: List<String>
    ) = Result(
        address = addressValue,
        category = "hotel",
        hu_free_cancellation = true,
        isHotel = true,
        description = descriptionValue,
        smallDescription = smallDescriptionValue,
        id = "AT7987",
        name = nameValue,
        stars = 4,
        url = "",
        featuredItem = buildFeaturedItem(),
        image = "http://media.omnibees.com/Images/8821/Property/394671.jpg",
        price = buildPriceResult(),
        sku = "",
        quantityDescriptors = QuantityDescriptors(
            maxAdults = 2,
            maxChildren = 2,
            maxFreeChildrenAge = 7
        ),
        tags = tagsValue,
        amenities = listOf(
            AmenityResults(
                "banheiro",
                "chuveiro quente"
            )
        ),
        gallery = listOf(Gallery(description = "", url = ""))
    )
}
