package com.br.natanbrito.challenge.data.model.filters

data class Filters(
    val amenities: List<Amenity>,
    val cities: List<City>,
    val countries: List<Country>,
    val priceInterval: PriceInterval,
    val prices: List<Price>,
    val productType: List<ProductType>,
    val rooms: List<Room>,
    val stars: List<Star>,
    val states: List<State>
)

data class Amenity(
    val count: Int,
    val filter: String,
    val term: String
)

data class City(
    val count: Int,
    val filter: String,
    val term: String
)

data class Country(
    val count: Int,
    val filter: String,
    val term: String
)

data class PriceInterval(
    val filterPattern: String,
    val max: Int,
    val min: Int
)

data class ProductType(
    val count: Int,
    val filter: String,
    val term: String
)

data class Room(
    val count: Int,
    val filter: String,
    val term: String
)

data class Star(
    val count: Int,
    val filter: String,
    val term: String
)

data class State(
    val count: Int,
    val filter: String,
    val term: String
)