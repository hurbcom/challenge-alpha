package com.hylsonk.myapplication.Model

class Address {
    private var city: String? = null

    fun getCity(): String? {
        return this.city
    }

    fun setCity(city: String) {
        this.city = city
    }

    private var country: String? = null

    fun getCountry(): String? {
        return this.country
    }

    fun setCountry(country: String) {
        this.country = country
    }

    private var id_city: Int? = null

    fun getIdCity(): Int? {
        return this.id_city
    }

    fun setIdCity(id_city: Int?) {
        this.id_city = id_city
    }

    private var id_country: Int = 0

    fun getIdCountry(): Int {
        return this.id_country
    }

    fun setIdCountry(id_country: Int) {
        this.id_country = id_country
    }

    private var id_state: Int = 0

    fun getIdState(): Int {
        return this.id_state
    }

    fun setIdState(id_state: Int) {
        this.id_state = id_state
    }

    private var state: String? = null

    fun getState(): String? {
        return this.state
    }

    fun setState(state: String) {
        this.state = state
    }

    private var street: String? = null

    fun getStreet(): String? {
        return this.street
    }

    fun setStreet(street: String) {
        this.street = street
    }

    private var zipcode: String? = null

    fun getZipcode(): String? {
        return this.zipcode
    }

    fun setZipcode(zipcode: String) {
        this.zipcode = zipcode
    }

    private var geoLocation: GeoLocation? = null

    fun getGeoLocation(): GeoLocation? {
        return this.geoLocation
    }

    fun setGeoLocation(geoLocation: GeoLocation) {
        this.geoLocation = geoLocation
    }
}