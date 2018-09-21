package com.hylsonk.myapplication.Model

class FeaturedItem {
    private var amenities: ArrayList<String>? = null

    fun getAmenities(): ArrayList<String>? {
        return this.amenities
    }

    fun setAmenities(amenities: ArrayList<String>) {
        this.amenities = amenities
    }

    private var name: String? = null

    fun getName(): String? {
        return this.name
    }

    fun setName(name: String) {
        this.name = name
    }

    private var image: String? = null

    fun getImage(): String? {
        return this.image
    }

    fun setImage(image: String) {
        this.image = image
    }

    private var description: String? = null

    fun getDescription(): String? {
        return this.description
    }

    fun setDescription(description: String) {
        this.description = description
    }

    private var hasInternet: Boolean? = null

    fun getHasInternet(): Boolean? {
        return this.hasInternet
    }

    fun setHasInternet(hasInternet: Boolean?) {
        this.hasInternet = hasInternet
    }

    private var hasParking: Boolean? = null

    fun getHasParking(): Boolean? {
        return this.hasParking
    }

    fun setHasParking(hasParking: Boolean?) {
        this.hasParking = hasParking
    }

    private var hasAccessbility: Boolean? = null

    fun getHasAccessbility(): Boolean? {
        return this.hasAccessbility
    }

    fun setHasAccessbility(hasAccessbility: Boolean?) {
        this.hasAccessbility = hasAccessbility
    }
}