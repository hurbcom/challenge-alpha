package com.belfortdev.hurbchallenge.core.model

import com.google.gson.annotations.SerializedName

object SearchResponse {

    data class FullResponse(
            @SerializedName("meta") val meta: Meta?,
            @SerializedName("filters") val filters: Filters?,
            @SerializedName("results") val results: List<Hotel?>?,
            @SerializedName("pagination") val pagination: Pagination?
    )

    data class Hotel(
            @SerializedName("id") val id: String,
            @SerializedName("name") val name: String?,
            @SerializedName("price") val price: HotelPrice?,
            @SerializedName("address") val address: Address?,
            @SerializedName("stars") val stars: Float?,
            @SerializedName("image") val image: String?,
            @SerializedName("amenities") val amenities: List<Amenity?>?,
            @SerializedName("sku") val sku: String?,
            @SerializedName("isPackage") val isPackage: Boolean?,
            @SerializedName("url") val url: String?,
            @SerializedName("smallDescription") val smallDescription: String?,
            @SerializedName("description") val description: String?,
            @SerializedName("category") val category: String?,
            @SerializedName("isHotel") val isHotel: Boolean?,
            @SerializedName("hu_free_cancellation") val huFreeCancellation: Boolean?
    )

    data class HotelPrice(
            @SerializedName("cost") val cost: Double?,
            @SerializedName("cost_fee") val costFee: Double?,
            @SerializedName("cost_price") val costPrice: Double?,
            @SerializedName("current_price") val currentPrice: Double?,
            @SerializedName("old_price") val oldPrice: Double?,
            @SerializedName("originalAmountPerDay") val originalAmountPerDay: Double?,
            @SerializedName("amountPerDay") val amountPerDay: Double?,
            @SerializedName("amount") val amount: Double?
    )

    data class Address(
            @SerializedName("city") val city: String?,
            @SerializedName("country") val country: String?,
            @SerializedName("id_city") val idCity: Int?,
            @SerializedName("id_country") val idCountry: Int?,
            @SerializedName("id_state") val idState: Int?,
            @SerializedName("state") val state: String?,
            @SerializedName("street") val street: String?,
            @SerializedName("zipcode") val zipcode: String?
    )

    data class Amenity(
            @SerializedName("name") val name: String?,
            @SerializedName("category") val category: String?
    )

    data class Filters(
            @SerializedName("attributes") val attributes: List<Attribute?>?,
            @SerializedName("countries") val countries: List<Country?>?,
            @SerializedName("cities") val cities: List<City?>?,
            @SerializedName("departureCities") val departureCities: List<DepartureCity?>?,
            @SerializedName("district") val district: List<District?>?,
            @SerializedName("duration") val duration: List<Duration?>?,
            @SerializedName("food") val food: List<Food?>?,
            @SerializedName("people") val people: List<People?>?,
            @SerializedName("prices") val prices: List<Price?>?,
            @SerializedName("priceInterval") val priceInterval: PriceInterval?,
            @SerializedName("productType") val productType: List<ProductType?>?,
            @SerializedName("regulation") val regulation: List<Regulation?>?,
            @SerializedName("rooms") val rooms: List<Room?>?,
            @SerializedName("stars") val stars: List<Star?>?,
            @SerializedName("states") val states: List<State?>?
    )

    data class Room(
            @SerializedName("term") val term: String?,
            @SerializedName("filter") val filter: String?,
            @SerializedName("count") val count: Int?
    )

    data class People(
            @SerializedName("term") val term: String?,
            @SerializedName("filter") val filter: String?,
            @SerializedName("count") val count: Int?
    )

    data class District(
            @SerializedName("term") val term: String?,
            @SerializedName("filter") val filter: String?,
            @SerializedName("count") val count: Int?
    )

    data class State(
            @SerializedName("term") val term: String?,
            @SerializedName("filter") val filter: String?,
            @SerializedName("count") val count: Int?
    )

    data class DepartureCity(
            @SerializedName("term") val term: String?,
            @SerializedName("filter") val filter: String?,
            @SerializedName("count") val count: Int?
    )

    data class Attribute(
            @SerializedName("term") val term: String?,
            @SerializedName("filter") val filter: String?,
            @SerializedName("count") val count: Int?
    )

    data class ProductType(
            @SerializedName("term") val term: String?,
            @SerializedName("filter") val filter: String?,
            @SerializedName("count") val count: Int?
    )

    data class Price(
            @SerializedName("min") val min: Int?,
            @SerializedName("maxExclusive") val maxExclusive: Int?,
            @SerializedName("filter") val filter: String?,
            @SerializedName("count") val count: Int?
    )

    data class PriceInterval(
            @SerializedName("min") val min: Int?,
            @SerializedName("max") val max: Int?,
            @SerializedName("filterPattern") val filterPattern: String?
    )

    data class City(
            @SerializedName("term") val term: String?,
            @SerializedName("filter") val filter: String?,
            @SerializedName("count") val count: Int?
    )

    data class Food(
            @SerializedName("term") val term: String?,
            @SerializedName("filter") val filter: String?,
            @SerializedName("count") val count: Int?
    )

    data class Star(
            @SerializedName("term") val term: String?,
            @SerializedName("filter") val filter: String?,
            @SerializedName("count") val count: Int?
    )

    data class Duration(
            @SerializedName("term") val term: String?,
            @SerializedName("filter") val filter: String?,
            @SerializedName("count") val count: Int?
    )

    data class Country(
            @SerializedName("term") val term: String?,
            @SerializedName("filter") val filter: String?,
            @SerializedName("count") val count: Int?
    )

    data class Regulation(
            @SerializedName("term") val term: String?,
            @SerializedName("filter") val filter: String?,
            @SerializedName("count") val count: Int?
    )

    data class Meta(
            @SerializedName("count") val count: Int?,
            @SerializedName("offset") val offset: Int?,
            @SerializedName("query") val query: String?,
            @SerializedName("warning") val warning: String?,
            @SerializedName("countWithAvailabilityInPage") val countWithAvailabilityInPage: Int?,
            @SerializedName("responseTime") val responseTime: ResponseTime?,
            @SerializedName("countHotel") val countHotel: Int?,
            @SerializedName("countPackage") val countPackage: Int?
    )

    data class ResponseTime(
            @SerializedName("searchEngine") val searchEngine: Int?,
            @SerializedName("total") val total: Int?
    )

    data class Pagination(
            @SerializedName("count") val count: Int?,
            @SerializedName("firstPage") val firstPage: String?,
            @SerializedName("nextPage") val nextPage: String?,
            @SerializedName("previousPage") val previousPage: Any?,
            @SerializedName("lastPage") val lastPage: String?
    )

}