package br.com.hurbandroidchallenge.presentation.compose.widgets.image

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.hurbandroidchallenge.commom.extension.noRippleClickable
import coil.compose.rememberAsyncImagePainter

@Composable
fun SmallCategoryItemPlaceholder() {
    Column(
        modifier = Modifier.width(86.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImagePlaceholder(modifier = Modifier
            .size(48.dp)
            .clip(CircleShape))
        Spacer(modifier = Modifier.height(32.dp))
    }
}