package com.hylsonk.myapplication.Model

class District {
    private var term: String? = null

    fun getTerm(): String? {
        return this.term
    }

    fun setTerm(term: String) {
        this.term = term
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