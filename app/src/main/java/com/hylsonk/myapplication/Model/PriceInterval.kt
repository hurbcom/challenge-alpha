package com.hylsonk.myapplication.Model

class PriceInterval {
    private var min: Int = 0

    fun getMin(): Int {
        return this.min
    }

    fun setMin(min: Int) {
        this.min = min
    }

    private var max: Int = 0

    fun getMax(): Int {
        return this.max
    }

    fun setMax(max: Int) {
        this.max = max
    }

    private var filterPattern: String? = null

    fun getFilterPattern(): String? {
        return this.filterPattern
    }

    fun setFilterPattern(filterPattern: String) {
        this.filterPattern = filterPattern
    }
}