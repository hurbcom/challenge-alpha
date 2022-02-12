package com.isranascimento.utils.extensions

import android.view.View

fun View.setVisible(isVisible: Boolean) {
    if(isVisible) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}