package br.com.hurbandroidchallenge.presentation.compose.components.lazy_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import br.com.hurbandroidchallenge.commom.extension.isNearToTheEnd
import br.com.hurbandroidchallenge.domain.model.ItemModel
import br.com.hurbandroidchallenge.presentation.compose.components.card.DummyItemCard
import br.com.hurbandroidchallenge.presentation.compose.components.card.ItemCard

@Composable
fun PagedItemList(
    modifier: Modifier = Modifier,
    items: List<ItemModel>,
    onItemClick: (url: String) -> Unit,
    loadMore: () -> Unit,
    isLoading: Boolean
) {
    val state = rememberLazyListState()
    if (state.isNearToTheEnd() and isLoading.not())
        loadMore()
    LazyColumn(
        modifier = modifier,
        state = state,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(all = 16.dp)
    ) {
        items(items) { item ->
            val shape = MaterialTheme.shapes.large
            ItemCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape)
                    .clickable {
                        onItemClick(item.url)
                    },
                itemModel = item,
                shape = shape
            )
        }
        item {
            if (isLoading) {
                DummyItemCard(aspectRadio = items.first().aspectRatio)
            }
        }
    }
}