package br.com.hurbandroidchallenge.presentation.compose.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

enum class NavScreens(
    val title: String,
    val icon: ImageVector,
) {
    Categories(title = "Categories", icon = Icons.Default.Home),
    Favorites(title = "Favorites", icon = Icons.Default.Star),
    LastSeen(title = "Last seen", icon = Icons.Default.History);
}