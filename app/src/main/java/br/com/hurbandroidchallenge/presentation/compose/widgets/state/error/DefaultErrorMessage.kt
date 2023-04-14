package br.com.hurbandroidchallenge.presentation.compose.widgets.state.error

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DefaultErrorScreen(
    message: String = "Ocorreu um erro inesperado"
) {
    Box(modifier = Modifier.fillMaxSize().padding(all = 16.dp)) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = message,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Composable
fun DefaultErrorText(
    modifier: Modifier = Modifier,
    message: String = "Ocorreu um erro inesperado"
) {
    Box(modifier = modifier.fillMaxWidth().padding(all = 16.dp)) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = message,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}