package br.com.hurbandroidchallenge.presentation.compose.components.card

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.hurbandroidchallenge.domain.model.ItemModel
import br.com.hurbandroidchallenge.presentation.compose.widgets.divider.DefaultDivider
import br.com.hurbandroidchallenge.presentation.compose.widgets.image.DefaultImage
import coil.compose.rememberAsyncImagePainter

@Composable
fun ItemCard(
    modifier: Modifier = Modifier,
    itemModel: ItemModel,
    shape: Shape = MaterialTheme.shapes.large
) {
    Card(modifier = modifier, shape = shape) {
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .fillMaxWidth()
                .aspectRatio(itemModel.aspectRatio * 2.1f)
        ) {
            DefaultImage(
                image = rememberAsyncImagePainter(model = itemModel.image),
                contentScale = itemModel.contentScale,
                aspectRadio = itemModel.aspectRatio,
                shape = shape
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(all = 16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                itemModel.fields.forEachIndexed { index, item ->
                    if (index != 0)
                        DefaultDivider(thickness = 1.dp)
                    ItemField(item.first, item.second)
                }
            }
        }
    }
}

@Composable
fun ItemField(
    title: String,
    content: String,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(2.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold
        )
        if (content.isNotBlank()) {
            Text(
                text = content,
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Start,
            )
        }
    }
}