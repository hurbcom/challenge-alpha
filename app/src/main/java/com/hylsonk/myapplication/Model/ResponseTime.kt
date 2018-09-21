package com.hylsonk.myapplication.Model

class ResponseTime {
    private var searchEngine: Int = 0

    fun getSearchEngine(): Int {
        return this.searchEngine
    }

    fun setSearchEngine(searchEngine: Int) {
        this.searchEngine = searchEngine
    }

    private var total: Int = 0

    fun getTotal(): Int {
        return this.total
    }

    fun setTotal(total: Int) {
        this.total = total
    }
}