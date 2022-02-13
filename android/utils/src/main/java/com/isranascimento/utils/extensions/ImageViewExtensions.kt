package com.isranascimento.utils.extensions

import android.widget.ImageView
import com.isranascimento.utils.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

fun ImageView.load(url: String?) {
    if(url?.isNotBlank() == true) {
        Picasso
            .get()
            .load(url)
            .error(R.drawable.ic_image_error)
            .into(this, object: Callback {
                override fun onSuccess() {}

                override fun onError(e: Exception?) {
                    this@load.scaleType = ImageView.ScaleType.CENTER
                }
            })
    }
}
