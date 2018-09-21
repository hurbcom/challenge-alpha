package com.hylsonk.myapplication.Model

class RootObject {
    private var meta: Meta? = null

    fun getMeta(): Meta? {
        return this.meta
    }

    fun setMeta(meta: Meta) {
        this.meta = meta
    }

    private var filters: Filters? = null

    fun getFilters(): Filters? {
        return this.filters
    }

    fun setFilters(filters: Filters) {
        this.filters = filters
    }

    private var results: ArrayList<Result>? = null

    fun getResults(): ArrayList<Result>? {
        return this.results
    }

    fun setResults(results: ArrayList<Result>) {
        this.results = results
    }

    private var pagination: Pagination? = null

    fun getPagination(): Pagination? {
        return this.pagination
    }

    fun setPagination(pagination: Pagination) {
        this.pagination = pagination
    }
}