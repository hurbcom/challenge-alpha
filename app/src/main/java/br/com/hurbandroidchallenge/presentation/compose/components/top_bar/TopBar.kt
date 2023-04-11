package br.com.hurbandroidchallenge.presentation.compose.components.top_bar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    onBackPressed: (() -> Unit)? = null,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    actions: @Composable RowScope.() -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(text = title, maxLines = 1, overflow = TextOverflow.Ellipsis)
        },
        navigationIcon = {
            if (onBackPressed != null) {
                TopBarIcon(onClick = { onBackPressed() }, imageVector = Icons.Default.ArrowBack)
            }
        },
        actions = actions,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = containerColor,
            actionIconContentColor = contentColorFor(containerColor),
            navigationIconContentColor = contentColorFor(containerColor),
            titleContentColor = contentColorFor(containerColor)
        ),
        scrollBehavior = scrollBehavior
    )
}