package com.hylsonk.myapplication.Model

class Filters {
    private var attributes: ArrayList<Attribute>? = null

    fun getAttributes(): ArrayList<Attribute>? {
        return this.attributes
    }

    fun setAttributes(attributes: ArrayList<Attribute>) {
        this.attributes = attributes
    }

    private var countries: ArrayList<Country>? = null

    fun getCountries(): ArrayList<Country>? {
        return this.countries
    }

    fun setCountries(countries: ArrayList<Country>) {
        this.countries = countries
    }

    private var cities: ArrayList<City>? = null

    fun getCities(): ArrayList<City>? {
        return this.cities
    }

    fun setCities(cities: ArrayList<City>) {
        this.cities = cities
    }

    private var departureCities: ArrayList<DepartureCity>? = null

    fun getDepartureCities(): ArrayList<DepartureCity>? {
        return this.departureCities
    }

    fun setDepartureCities(departureCities: ArrayList<DepartureCity>) {
        this.departureCities = departureCities
    }

    private var district: ArrayList<District>? = null

    fun getDistrict(): ArrayList<District>? {
        return this.district
    }

    fun setDistrict(district: ArrayList<District>) {
        this.district = district
    }

    private var duration: ArrayList<Duration>? = null

    fun getDuration(): ArrayList<Duration>? {
        return this.duration
    }

    fun setDuration(duration: ArrayList<Duration>) {
        this.duration = duration
    }

    private var food: ArrayList<Food>? = null

    fun getFood(): ArrayList<Food>? {
        return this.food
    }

    fun setFood(food: ArrayList<Food>) {
        this.food = food
    }

    private var people: ArrayList<Person>? = null

    fun getPeople(): ArrayList<Person>? {
        return this.people
    }

    fun setPeople(people: ArrayList<Person>) {
        this.people = people
    }

    private var prices: ArrayList<Price>? = null

    fun getPrices(): ArrayList<Price>? {
        return this.prices
    }

    fun setPrices(prices: ArrayList<Price>) {
        this.prices = prices
    }

    private var priceInterval: PriceInterval? = null

    fun getPriceInterval(): PriceInterval? {
        return this.priceInterval
    }

    fun setPriceInterval(priceInterval: PriceInterval) {
        this.priceInterval = priceInterval
    }

    private var productType: ArrayList<ProductType>? = null

    fun getProductType(): ArrayList<ProductType>? {
        return this.productType
    }

    fun setProductType(productType: ArrayList<ProductType>) {
        this.productType = productType
    }

    private var regulation: ArrayList<Regulation>? = null

    fun getRegulation(): ArrayList<Regulation>? {
        return this.regulation
    }

    fun setRegulation(regulation: ArrayList<Regulation>) {
        this.regulation = regulation
    }

    private var rooms: ArrayList<Room>? = null

    fun getRooms(): ArrayList<Room>? {
        return this.rooms
    }

    fun setRooms(rooms: ArrayList<Room>) {
        this.rooms = rooms
    }

    private var stars: ArrayList<Star>? = null

    fun getStars(): ArrayList<Star>? {
        return this.stars
    }

    fun setStars(stars: ArrayList<Star>) {
        this.stars = stars
    }

    private var states: ArrayList<State>? = null

    fun getStates(): ArrayList<State>? {
        return this.states
    }

    fun setStates(states: ArrayList<State>) {
        this.states = states
    }
}