package com.example.hurb_challenge.app.presentation.common.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextDescription(title: String, subtitle: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 6.dp, vertical = 6.dp),
            fontSize = 16.sp,
            color = Color(0xFF818181),
            style = MaterialTheme.typography.titleMedium,
        )
        Text(
            text = subtitle,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 2.dp),
            fontSize = 14.sp,
            color = Color.Black,
            style = MaterialTheme.typography.titleMedium,
        )
    }
}
