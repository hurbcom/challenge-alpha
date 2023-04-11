package br.com.hurbandroidchallenge.presentation.compose.widgets.lazy_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.hurbandroidchallenge.commom.extension.isScrolledToTheEnd

@Composable
fun PagedLazyColumn(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    loadMore: () -> Unit,
    verticalArrangement: Arrangement.HorizontalOrVertical = Arrangement.spacedBy(8.dp),
    contentPadding: PaddingValues = PaddingValues(all = 16.dp),
    content: LazyListScope.() -> Unit
) {
    val state = rememberLazyListState()
    if (state.isScrolledToTheEnd() and isLoading.not())
        loadMore()
    LazyColumn(
        modifier = modifier,
        state = state,
        verticalArrangement = verticalArrangement,
        contentPadding = contentPadding
    ) {
        content()
        item {
            if (isLoading) {
                LaunchedEffect(Unit) {
                    state.animateScrollToItem(state.layoutInfo.totalItemsCount - 1)
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                ) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center), color = MaterialTheme.colorScheme.secondary)
                }
            }
        }
    }
}