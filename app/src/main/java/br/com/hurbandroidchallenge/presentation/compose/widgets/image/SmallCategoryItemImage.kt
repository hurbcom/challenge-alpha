package br.com.hurbandroidchallenge.presentation.compose.widgets.image

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.hurbandroidchallenge.commom.extension.noRippleClickable
import coil.compose.rememberAsyncImagePainter

@Composable
fun SmallCategoryItemImage(
    text: String,
    textColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    onClick: () -> Unit,
    image: String,
) {
    Column(
        modifier = Modifier
            .width(86.dp)
            .noRippleClickable { onClick() },
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DefaultImage(
            image = rememberAsyncImagePainter(model = image),
            shape = CircleShape,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(48.dp)
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = text,
            color = textColor,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )
    }
}