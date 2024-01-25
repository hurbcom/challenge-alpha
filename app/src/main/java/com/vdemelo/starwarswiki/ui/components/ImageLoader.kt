package com.vdemelo.starwarswiki.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.vdemelo.starwarswiki.R

@Composable
fun ImageLoader(
    modifier: Modifier = Modifier,
    url: String?,
    contentDescription: String
) {
    if (url != null) {
        AsyncImage(
            modifier = modifier,
            model = url,
            contentDescription = contentDescription
        )
    } else {
        Image(
            modifier = modifier,
            painter = painterResource(id = R.drawable.image_placeholder),
            contentDescription = contentDescription
        )
    }
}
