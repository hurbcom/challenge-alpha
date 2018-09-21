package com.hylsonk.myapplication.Model

class Gallery {
    private var description: String? = null

    fun getDescription(): String? {
        return this.description
    }

    fun setDescription(description: String) {
        this.description = description
    }

    private var url: String? = null

    fun getUrl(): String? {
        return this.url
    }

    fun setUrl(url: String) {
        this.url = url
    }
}