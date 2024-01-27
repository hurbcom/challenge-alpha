package com.vdemelo.starwarswiki.ui.screens.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vdemelo.starwarswiki.R
import com.vdemelo.starwarswiki.domain.entity.model.TextField
import com.vdemelo.starwarswiki.ui.components.ImageLoader
import com.vdemelo.starwarswiki.ui.components.LabelAndTextData
import com.vdemelo.starwarswiki.ui.theme.ComposeStarWarsTheme
import com.vdemelo.starwarswiki.utils.simpleCapitalize

@Composable
fun DetailsScreen(
    title: String,
    imageUrl: String?,
    fields: List<TextField>
) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 32.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = title,
                color = Color.Black,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(32.dp))
            ImageLoader(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .heightIn(min = 0.dp, 200.dp),
                url = imageUrl,
                contentDescription = stringResource(
                    id = R.string.details_screen_image_content_description
                )
            )
            fields.forEach {
                Spacer(modifier = Modifier.height(32.dp))
                LabelAndTextData(label = it.label, text = it.text)
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview(
    title: String = "Titulo dos Detalhes",
    imageUrl: String? = "https://starwars-visualguide.com/assets/img/species/1.jpg",
    fields: List<TextField> = listOf(
        TextField("label top", "valor top"),
        TextField("label top", "valor top"),
        TextField("label top", "valor top"),
        TextField("label top", "valor top")
    )
) {
    ComposeStarWarsTheme {
        DetailsScreen(title, imageUrl, fields)
    }
}
