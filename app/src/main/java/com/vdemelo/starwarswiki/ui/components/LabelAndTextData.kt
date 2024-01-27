package com.vdemelo.starwarswiki.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.vdemelo.starwarswiki.R
import com.vdemelo.starwarswiki.utils.simpleCapitalize

@Composable
fun LabelAndTextData(
    label: String,
    text: String?
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = label,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = text?.simpleCapitalize() ?: stringResource(id = R.string.common_unknown),
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
    }
}
