package com.isranascimento.utils.extensions

import android.widget.ImageView
import androidx.annotation.DrawableRes
import coil.load
import com.isranascimento.utils.R
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

fun ImageView.load(@DrawableRes resourceId: Int) {
    if(resourceId != 0) {
        this.setImageResource(resourceId)
    }
}