package com.example.hurb_challenge.app.presentation.common.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hurb_challenge.R
import com.example.hurb_challenge.app.core.decodeUrl
import com.example.hurb_challenge.app.domain.model.CardItem
import com.example.hurb_challenge.app.domain.model.ImageUrlProvider

@Composable
fun <T> Card(cardItem: CardItem<T>, onItemClicked: (T) -> Unit) {
    Card(
        modifier =
        Modifier
            .padding(6.dp)
            .border(1.dp, Color.LightGray, RoundedCornerShape(10.dp))
            .clickable { onItemClicked.invoke(cardItem.item) }
    ) {
        Column(Modifier.background(Color.White)) {
            AsyncImageLoader(
                url = (cardItem.item as? ImageUrlProvider)?.getImageUrl() ?: "",
                modifier = Modifier
                    .fillMaxWidth()
                    .size(180.dp),
                loadingContent = {
                    CircularProgress(
                        Modifier
                            .size(60.dp)
                            .align(Alignment.Center)
                    )
                },
                failureContent = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_img_placeholder),
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentDescription = null
                    )
                },
                loadedContent = { painter ->
                    Image(
                        painter = painter,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentDescription = null
                    )
                }
            )
            Text(
                cardItem.title,
                fontSize = 16.sp,
                color = Color.DarkGray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 6.dp, vertical = 4.dp),
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                cardItem.subtitle.decodeUrl(),
                fontSize = 13.sp,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 6.dp, bottom = 6.dp, top = 2.dp),
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}
