package com.example.core.base

import android.view.View

object ViewExtensions {
    fun View.hide() {
        visibility = View.GONE
    }

    fun View.show() {
        visibility = View.VISIBLE
    }
}