package com.isranascimento.utils.extensions

import android.widget.ImageView
import coil.load
import com.isranascimento.utils.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

fun ImageView.load(url: String?, scaleType: ImageView.ScaleType = ImageView.ScaleType.CENTER_CROP) {
    if(url?.isNotBlank() == true) {
        this.scaleType = scaleType
        this.load(url) {
            crossfade(true)
            this.error(R.drawable.ic_image_error)
        }
    }
}
