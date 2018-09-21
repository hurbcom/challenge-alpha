package com.hylsonk.myapplication.Model

class Meta {
    private var count: Int = 0

    fun getCount(): Int {
        return this.count
    }

    fun setCount(count: Int) {
        this.count = count
    }

    private var offset: Int = 0

    fun getOffset(): Int {
        return this.offset
    }

    fun setOffset(offset: Int) {
        this.offset = offset
    }

    private var query: String? = null

    fun getQuery(): String? {
        return this.query
    }

    fun setQuery(query: String) {
        this.query = query
    }

    private var warning: String? = null

    fun getWarning(): String? {
        return this.warning
    }

    fun setWarning(warning: String) {
        this.warning = warning
    }

    private var countWithAvailabilityInPage: Int = 0

    fun getCountWithAvailabilityInPage(): Int {
        return this.countWithAvailabilityInPage
    }

    fun setCountWithAvailabilityInPage(countWithAvailabilityInPage: Int) {
        this.countWithAvailabilityInPage = countWithAvailabilityInPage
    }

    private var responseTime: ResponseTime? = null

    fun getResponseTime(): ResponseTime? {
        return this.responseTime
    }

    fun setResponseTime(responseTime: ResponseTime) {
        this.responseTime = responseTime
    }

    private var countHotel: Int = 0

    fun getCountHotel(): Int {
        return this.countHotel
    }

    fun setCountHotel(countHotel: Int) {
        this.countHotel = countHotel
    }

    private var countPackage: Int = 0

    fun getCountPackage(): Int {
        return this.countPackage
    }

    fun setCountPackage(countPackage: Int) {
        this.countPackage = countPackage
    }

}