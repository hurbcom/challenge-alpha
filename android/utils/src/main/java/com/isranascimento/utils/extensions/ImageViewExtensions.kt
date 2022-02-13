package com.isranascimento.utils.extensions

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.load(url: String?) {
    if(url?.isNotBlank() == true) {
        Picasso
            .get()
            .load(url)
            .into(this)
    }
}
