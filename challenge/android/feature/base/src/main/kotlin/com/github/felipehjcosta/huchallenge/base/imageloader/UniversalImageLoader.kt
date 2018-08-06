package com.github.felipehjcosta.huchallenge.base.imageloader

import android.graphics.Bitmap
import android.widget.ImageView
import com.github.felipehjcosta.huchallenge.base.R
import com.nostra13.universalimageloader.core.DisplayImageOptions

internal class UniversalImageLoader(
        private val imageLoader: com.nostra13.universalimageloader.core.ImageLoader
) : ImageLoader {

    override fun loadImage(url: String?, imageView: ImageView) {
        val imageOptions = DisplayImageOptions.Builder()
                .showImageOnLoading(R.color.image_default_color)
                .showImageForEmptyUri(R.color.image_default_color)
                .showImageOnFail(R.color.image_default_color)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build()

        imageLoader.displayImage(url, imageView, imageOptions)
    }
}