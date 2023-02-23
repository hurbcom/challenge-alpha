package com.example.core.base.presentation

import android.view.View

object BaseViewExtensions {
    fun View.hide() {
        visibility = View.GONE
    }

    fun View.show() {
        visibility = View.VISIBLE
    }
}