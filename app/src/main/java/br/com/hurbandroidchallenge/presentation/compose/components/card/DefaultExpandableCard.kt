package br.com.hurbandroidchallenge.presentation.compose.components.card

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import br.com.hurbandroidchallenge.presentation.compose.animation.SlideVertically

@Composable
fun DefaultExpandableCard(
    name: String,
    content: @Composable () -> Unit
) {
    Column {
        Card(modifier = Modifier.fillMaxWidth()) {
            val rotation = remember { Animatable(0f) }
            var showOtherCategory by remember { mutableStateOf(false) }
            if (showOtherCategory) {
                LaunchedEffect(Unit) {
                    rotation.animateTo(180f)
                }
            } else {
                LaunchedEffect(Unit) {
                    rotation.animateTo(0f)
                }
            }
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            showOtherCategory = !showOtherCategory
                        }
                        .padding(all = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Icon(
                        modifier = Modifier.rotate(rotation.value),
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null
                    )
                }
                SlideVertically(visible = showOtherCategory) {
                    Surface(
                        shape = MaterialTheme.shapes.large.copy(
                            topEnd = CornerSize(0.dp),
                            topStart = CornerSize(0.dp)
                        ),
                        color = MaterialTheme.colorScheme.surfaceVariant
                    ) {
                        content()
                    }
                }
            }
        }
    }
}