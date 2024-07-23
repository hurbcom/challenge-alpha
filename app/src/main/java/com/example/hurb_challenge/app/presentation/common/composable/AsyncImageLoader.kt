package com.example.hurb_challenge.app.presentation.common.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.hurb_challenge.app.core.decodeUrl

@Composable
fun AsyncImageLoader(
     url: String,
     modifier: Modifier = Modifier,
     loadingContent: @Composable BoxScope.() -> Unit,
     failureContent: @Composable BoxScope.() -> Unit,
     loadedContent: @Composable BoxScope.(AsyncImagePainter) -> Unit
     ) {
     val painter = rememberAsyncImagePainter(
          model = ImageRequest.Builder(LocalContext.current)
               .data(url.decodeUrl())
               .size(coil.size.Size.ORIGINAL)
               .build()
     )

     Box(modifier = modifier) {
          when (painter.state) {
               is AsyncImagePainter.State.Loading -> { loadingContent() }
               is AsyncImagePainter.State.Error -> { failureContent() }
               else -> { loadedContent(painter) }
          }
     }
}
