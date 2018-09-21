package com.hylsonk.myapplication.Model

class GeoLocation {
    private var lat: Double = 0.toDouble()

    fun getLat(): Double {
        return this.lat
    }

    fun setLat(lat: Double) {
        this.lat = lat
    }

    private var lon: Double = 0.toDouble()

    fun getLon(): Double {
        return this.lon
    }

    fun setLon(lon: Double) {
        this.lon = lon
    }
}