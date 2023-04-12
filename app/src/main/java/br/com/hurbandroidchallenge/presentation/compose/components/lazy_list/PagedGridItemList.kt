package br.com.hurbandroidchallenge.presentation.compose.components.lazy_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import br.com.hurbandroidchallenge.commom.extension.isScrolledToTheEnd
import br.com.hurbandroidchallenge.domain.model.ItemModel
import br.com.hurbandroidchallenge.presentation.compose.components.item_card.ItemCard
import br.com.hurbandroidchallenge.presentation.compose.components.item_card.DummyItemCard

@Composable
fun PagedGridItemList(
    modifier: Modifier = Modifier,
    categoryItems: List<ItemModel>,
    onItemClick: () -> Unit,
    aspectRatio: Float,
    isLoading: Boolean,
    loadMore: () -> Unit
) {
    val state = rememberLazyGridState()
    if (state.isScrolledToTheEnd() and isLoading.not())
        loadMore()
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        state = state,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(all = 16.dp)
    ) {
        items(categoryItems) { category ->
            ItemCard(
                modifier = Modifier
                    .clickable {
                        onItemClick()
                    },
                title = category.name,
                image = category.image,
                contentScale = ContentScale.FillWidth,
                aspectRadio = aspectRatio
            )
        }
        items(2) {
            if (isLoading) {
                LaunchedEffect(Unit) {
                    state.animateScrollToItem(state.layoutInfo.totalItemsCount - 1)
                }
                DummyItemCard(aspectRadio = aspectRatio)
            }
        }
    }
}