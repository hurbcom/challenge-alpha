package br.com.hurbandroidchallenge.presentation.compose.widgets.image

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.toSize

@Composable
fun ImagePlaceholder(modifier: Modifier = Modifier, icon: ImageVector) {
    var iconSize by remember { mutableStateOf(Size.Zero) }
    Box(modifier = modifier
        .background(color = MaterialTheme.colorScheme.outline)
        .onGloballyPositioned { iconSize = (it.size / 2).toSize() }) {
        Icon(
            modifier = Modifier
                .size(LocalDensity.current.run { iconSize.toDpSize() })
                .align(Alignment.Center),
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}