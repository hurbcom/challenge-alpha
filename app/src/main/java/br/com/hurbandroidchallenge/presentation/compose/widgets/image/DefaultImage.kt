package br.com.hurbandroidchallenge.presentation.compose.widgets.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImagePainter

@Composable
fun DefaultImage(
    image: AsyncImagePainter,
    aspectRadio: Float = 1f,
    contentScale: ContentScale = ContentScale.Fit,
    shape: Shape,
) {
    Box(modifier = Modifier.clip(shape)) {
        Image(
            modifier = Modifier.aspectRatio(aspectRadio),
            painter = image,
            contentDescription = null,
            contentScale = contentScale
        )
        if (image.state !is AsyncImagePainter.State.Success) {
            ImagePlaceholder(
                modifier = Modifier
                    .aspectRatio(aspectRadio),
                icon = Icons.Default.Image
            )
        }
    }
}