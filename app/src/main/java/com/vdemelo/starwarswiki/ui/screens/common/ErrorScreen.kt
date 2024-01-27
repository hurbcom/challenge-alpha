package com.vdemelo.starwarswiki.ui.screens.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ErrorScreen(
    error: String,
    buttonLabel: String,
    onRetry: () -> Unit
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = error, color = Color.Red, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { onRetry() },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = buttonLabel)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UnexpectedErrorRetryScreenPreview() {
    ErrorScreen(
        error = "An unexpected error occurred!",
        buttonLabel = "Retry"
    ) {}
}

@Preview(showBackground = true)
@Composable
fun UnexpectedErrorGoBackScreenPreview() {
    ErrorScreen(
        error = "An unexpected error occurred!",
        buttonLabel = "Go Back"
    ) {}
}
