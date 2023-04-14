package br.com.hurbandroidchallenge.commom.extension

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState

fun LazyGridState.isNearToTheEnd() =
    (layoutInfo.visibleItemsInfo.lastOrNull()?.index ifNull  0) == layoutInfo.totalItemsCount - 1

fun LazyListState.isNearToTheEnd() =
    (layoutInfo.visibleItemsInfo.lastOrNull()?.index ifNull 0) == layoutInfo.totalItemsCount - 1