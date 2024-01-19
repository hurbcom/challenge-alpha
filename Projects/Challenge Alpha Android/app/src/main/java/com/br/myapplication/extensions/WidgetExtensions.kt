package com.br.myapplication.extensions

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE

fun View.hide() {
    this.visibility = GONE
}

fun View.visible() {
    this.visibility = VISIBLE
}