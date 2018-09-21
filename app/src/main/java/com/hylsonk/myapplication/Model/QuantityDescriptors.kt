package com.hylsonk.myapplication.Model

class QuantityDescriptors {
    private var maxChildren: Int = 0

    fun getMaxChildren(): Int {
        return this.maxChildren
    }

    fun setMaxChildren(maxChildren: Int) {
        this.maxChildren = maxChildren
    }

    private var maxAdults: Int = 0

    fun getMaxAdults(): Int {
        return this.maxAdults
    }

    fun setMaxAdults(maxAdults: Int) {
        this.maxAdults = maxAdults
    }

    private var maxFreeChildrenAge: Int = 0

    fun getMaxFreeChildrenAge(): Int {
        return this.maxFreeChildrenAge
    }

    fun setMaxFreeChildrenAge(maxFreeChildrenAge: Int) {
        this.maxFreeChildrenAge = maxFreeChildrenAge
    }

    private var nights: Int? = null

    fun getNights(): Int? {
        return this.nights
    }

    fun setNights(nights: Int?) {
        this.nights = nights
    }

    private var maxPeople: Int? = null

    fun getMaxPeople(): Int? {
        return this.maxPeople
    }

    fun setMaxPeople(maxPeople: Int?) {
        this.maxPeople = maxPeople
    }
}