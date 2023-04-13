package br.com.hurbandroidchallenge.presentation.compose.widgets.image

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun SmallCategoryItemImage(
    text: String,
    textColor: Color,
    image: String,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DefaultImage(
            image = rememberAsyncImagePainter(model = image),
            shape = CircleShape,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(48.dp)
        )
        Text(text = text, color = textColor, style = MaterialTheme.typography.titleMedium)
    }
}