package br.com.hurbandroidchallenge.presentation.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.hurbandroidchallenge.domain.model.ItemModel
import br.com.hurbandroidchallenge.presentation.compose.components.card.ItemField
import br.com.hurbandroidchallenge.presentation.compose.widgets.divider.DefaultDivider
import br.com.hurbandroidchallenge.presentation.compose.widgets.image.DefaultImage
import coil.compose.rememberAsyncImagePainter

@Composable
fun CategoryItemDetail(
    itemModel: ItemModel,
    corners: Dp = 16.dp,
    otherCategories: @Composable () -> Unit,
) {
    val firstCardListSize = 4
    val totalSize = itemModel.fields.size
    val firstFields = itemModel.fields.subList(0, firstCardListSize - 1)
    val otherFields = itemModel.fields.subList(firstCardListSize, totalSize)
    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.background(color = MaterialTheme.colorScheme.surfaceVariant),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .height(IntrinsicSize.Min)
                    .fillMaxWidth()
            ) {
                DefaultImage(
                    image = rememberAsyncImagePainter(model = itemModel.image),
                    contentScale = itemModel.contentScale,
                    aspectRadio = itemModel.aspectRatio,
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        bottomStart = 0.dp,
                        topEnd = corners,
                        bottomEnd = corners
                    )
                )
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(all = 16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    firstFields.forEachIndexed { index, item ->
                        if (index != 0)
                            DefaultDivider(thickness = 1.dp)
                        ItemField(item.first, item.second)
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.Start
            ) {
                otherFields.forEachIndexed { index, item ->
                    if (index != 0)
                        DefaultDivider(thickness = 1.dp)
                    ItemField(item.first, item.second)
                }
            }
        }
        otherCategories()
    }
}