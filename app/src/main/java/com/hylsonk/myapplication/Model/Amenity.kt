package com.hylsonk.myapplication.Model

class Amenity {
    private var name: String? = null

    fun getName(): String? {
        return this.name
    }

    fun setName(name: String) {
        this.name = name
    }

    private var category: String? = null

    fun getCategory(): String? {
        return this.category
    }

    fun setCategory(category: String) {
        this.category = category
    }

}