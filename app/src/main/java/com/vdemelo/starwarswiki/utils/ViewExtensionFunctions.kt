package com.vdemelo.starwarswiki.utils

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible

fun View.gone() {
    this.isVisible = false
}

fun View.visible() {
    this.isVisible = true
}

fun AppCompatImageView.setImageOrHideView(imageUrl: String?) {
    if (imageUrl != null) {
        this.visible()
        //TODO baixar imagem e setar imagem
    } else {
        this.gone()
    }
}

fun AppCompatTextView.setTextOrHideView(text: String?) {
    if (text != null) {
        this.visible()
        this.text = text
    } else {
        this.gone()
    }
}
