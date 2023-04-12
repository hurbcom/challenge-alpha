package br.com.hurbandroidchallenge.presentation.compose.components.item_card

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import br.com.hurbandroidchallenge.presentation.compose.widgets.image.ImagePlaceholder

@Composable
fun DummyItemCard(
    aspectRadio: Float,
    shape: Shape = MaterialTheme.shapes.large,
) {
    Card(shape = shape) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.clip(shape)) {
                ImagePlaceholder(
                    modifier = Modifier.aspectRatio(aspectRadio),
                    icon = Icons.Default.Image
                )
            }
            Box(modifier = Modifier.padding(all = 16.dp))
        }
    }
}