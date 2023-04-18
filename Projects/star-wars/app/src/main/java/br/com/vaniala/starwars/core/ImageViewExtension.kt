package br.com.vaniala.starwars.core

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import coil.load
import coil.transform.CircleCropTransformation

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 17/04/23.
 *
 */
fun ImageView.loadImage(
    url: String? = null,
    progressBar: ProgressBar? = null,
    isCircle: Boolean = false,
) {
    load(url) {
        if (isCircle) transformations(CircleCropTransformation())
        listener(
            onError = { _, _ ->
                progressBar?.visibility = View.GONE
            },
            onSuccess = { _, _ ->
                progressBar?.visibility = View.GONE
            },
        )
    }
}
