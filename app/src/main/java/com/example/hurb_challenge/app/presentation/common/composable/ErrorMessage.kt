package com.example.hurb_challenge.app.presentation.common.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hurb_challenge.R

@Composable
fun ErrorMessage(message: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize(1f)
            .padding(vertical = 8.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = message,
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(18.dp))
        Button(
            onClick = onClick::invoke,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = stringResource(id = R.string.retry),
                modifier = Modifier
                    .padding(7.dp)
                    .fillMaxWidth(0.5f),
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}
