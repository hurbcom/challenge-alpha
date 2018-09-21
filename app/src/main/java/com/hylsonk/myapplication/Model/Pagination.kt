package com.hylsonk.myapplication.Model

class Pagination {
    private var count: Int = 0

    fun getCount(): Int {
        return this.count
    }

    fun setCount(count: Int) {
        this.count = count
    }

    private var firstPage: String? = null

    fun getFirstPage(): String? {
        return this.firstPage
    }

    fun setFirstPage(firstPage: String) {
        this.firstPage = firstPage
    }

    private var nextPage: String? = null

    fun getNextPage(): String? {
        return this.nextPage
    }

    fun setNextPage(nextPage: String) {
        this.nextPage = nextPage
    }

    private var previousPage: Any? = null

    fun getPreviousPage(): Any? {
        return this.previousPage
    }

    fun setPreviousPage(previousPage: Any) {
        this.previousPage = previousPage
    }

    private var lastPage: String? = null

    fun getLastPage(): String? {
        return this.lastPage
    }

    fun setLastPage(lastPage: String) {
        this.lastPage = lastPage
    }
}