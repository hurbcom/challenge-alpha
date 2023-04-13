package br.com.hurbandroidchallenge.presentation.compose.widgets.swipe_refresh

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.SwipeRefreshState

@Composable
fun DefaultSwipeRefresh(
    swipeRefreshState: SwipeRefreshState,
    onRefresh: () -> Unit,
    indicatorBackgroundColor: Color = MaterialTheme.colorScheme.surface,
    indicatorContentColor: Color = MaterialTheme.colorScheme.primary,
    indicatorScale: Boolean = true,
    content: @Composable () -> Unit

) {
    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = onRefresh,
        indicator = { state, trigger ->
            SwipeRefreshIndicator(
                state = state,
                refreshTriggerDistance = trigger,
                scale = indicatorScale,
                backgroundColor = indicatorBackgroundColor,
                contentColor = indicatorContentColor
            )
        }
    ) {
        content()
    }
}