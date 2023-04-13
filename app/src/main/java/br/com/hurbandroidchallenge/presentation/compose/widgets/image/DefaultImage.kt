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
    modifier: Modifier = Modifier,
    image: AsyncImagePainter,
    aspectRadio: Float = 1f,
    contentScale: ContentScale = ContentScale.Fit,
    shape: Shape
) {
    Box(modifier = modifier.clip(shape)) {
        Image(
            modifier = modifier.aspectRatio(aspectRadio),
            painter = image,
            contentDescription = null,
            contentScale = contentScale
        )
        if (image.state !is AsyncImagePainter.State.Success) {
            ImagePlaceholder(
                modifier = modifier.aspectRatio(aspectRadio),
                icon = Icons.Default.Image
            )
        }
    }
}