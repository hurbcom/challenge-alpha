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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import br.com.hurbandroidchallenge.commom.extension.isScrolledToTheEnd
import br.com.hurbandroidchallenge.domain.model.ItemModel
import br.com.hurbandroidchallenge.presentation.compose.components.card.DummyItemCard
import br.com.hurbandroidchallenge.presentation.compose.components.card.ItemCard

@Composable
fun PagedItemList(
    modifier: Modifier = Modifier,
    categoryItems: List<ItemModel>,
    onItemClick: () -> Unit,
    aspectRatio: Float,
    isLoading: Boolean,
    loadMore: () -> Unit
) {
    val state = rememberLazyListState()
    if (state.isScrolledToTheEnd() and isLoading.not())
        loadMore()
    LazyColumn(
        modifier = modifier,
        state = state,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(all = 16.dp)
    ) {

        items(categoryItems) { item ->
            val shape = MaterialTheme.shapes.large
            ItemCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape)
                    .clickable {
                        onItemClick()
                    },
                image = item.image,
                contentScale = ContentScale.FillWidth,
                aspectRadio = aspectRatio,
                itemFields = item.fields,
                shape = shape
            )
        }
        item {
            if (isLoading) {
                LaunchedEffect(Unit) {
                    state.animateScrollToItem(state.layoutInfo.totalItemsCount - 1)
                }
                DummyItemCard(aspectRadio = aspectRatio)
            }
        }
    }
}