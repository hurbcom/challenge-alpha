package br.com.hurbandroidchallenge.presentation.compose.widgets.state.loading

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DefaultLoadingScreen(
    color: Color = MaterialTheme.colorScheme.primary,
    text: String? = null
) {
    Box(modifier = Modifier.fillMaxSize().padding(all = 16.dp)) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CircularProgressIndicator(color = color)
            if (text != null) {
                Text(text = text, style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}