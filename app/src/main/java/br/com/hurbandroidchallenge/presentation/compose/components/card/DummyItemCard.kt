package br.com.hurbandroidchallenge.presentation.compose.components.card

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape

@Composable
fun DummyItemCard(
    aspectRatio: Float,
    shape: Shape = MaterialTheme.shapes.large,
) {
    Card(shape = shape) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(aspectRatio * 2.1f)
        ) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}