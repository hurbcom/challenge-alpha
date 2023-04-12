package br.com.hurbandroidchallenge.presentation.compose.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.hurbandroidchallenge.presentation.compose.widgets.image.DefaultImage
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ItemCard(
    modifier: Modifier = Modifier,
    title: String,
    image: String,
    aspectRadio: Float,
    shape: Shape = MaterialTheme.shapes.large,
    contentScale: ContentScale = ContentScale.FillHeight,
) {
    Card(modifier = modifier, shape = shape) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val scope = rememberCoroutineScope()
            DefaultImage(
                image = rememberAsyncImagePainter(model = image, onLoading = { scope.launch { delay(1000L) } }),
                contentScale = contentScale,
                aspectRadio = aspectRadio,
                shape = shape
            )
            Text(
                modifier = Modifier.padding(all = 16.dp),
                text = title,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}