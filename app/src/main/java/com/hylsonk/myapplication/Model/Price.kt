package com.hylsonk.myapplication.Model

class Price {
    private var min: Int = 0

    fun getMin(): Int {
        return this.min
    }

    fun setMin(min: Int) {
        this.min = min
    }

    private var maxExclusive: Int = 0

    fun getMaxExclusive(): Int {
        return this.maxExclusive
    }

    fun setMaxExclusive(maxExclusive: Int) {
        this.maxExclusive = maxExclusive
    }

    private var filter: String? = null

    fun getFilter(): String? {
        return this.filter
    }

    fun setFilter(filter: String) {
        this.filter = filter
    }

    private var count: Int = 0

    fun getCount(): Int {
        return this.count
    }

    fun setCount(count: Int) {
        this.count = count
    }
}