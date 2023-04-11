package br.com.hurbandroidchallenge.presentation.compose.widgets.divider

import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DefaultDivider(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurface,
    thickness: Dp = (0.5).dp
) {
    Divider(
        modifier = modifier,
        color = color.copy(alpha = 0.5f),
        thickness = thickness
    )
}