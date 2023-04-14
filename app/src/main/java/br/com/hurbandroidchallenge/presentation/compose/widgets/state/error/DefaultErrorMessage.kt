package br.com.hurbandroidchallenge.presentation.compose.widgets.state.error

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DefaultErrorScreen(
    refresh: () -> Unit = {},
    message: String = "An unexpected error has occurred",
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp)
            .clickable {
                refresh()
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = "Touch to refresh",
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
fun DefaultErrorText(
    modifier: Modifier = Modifier,
    message: String = "An unexpected error has occurred",
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 16.dp)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = message,
            style = MaterialTheme.typography.titleMedium
        )
    }
}