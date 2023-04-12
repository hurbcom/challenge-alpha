package br.com.hurbandroidchallenge.presentation.compose.widgets.image

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun ImagePlaceholder(modifier: Modifier = Modifier, icon: ImageVector) {
    Box(modifier = modifier.background(color = MaterialTheme.colorScheme.outline)) {
        Icon(
            modifier = Modifier
                .size(64.dp)
                .align(Alignment.Center),
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}