package com.github.felipehjcosta.huchallenge.base.imageloader

import android.widget.ImageView

interface ImageLoader {
    fun loadImage(url: String?, imageView: ImageView)
}