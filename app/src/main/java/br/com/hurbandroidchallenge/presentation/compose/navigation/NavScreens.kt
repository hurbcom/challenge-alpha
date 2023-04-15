package br.com.hurbandroidchallenge.presentation.compose.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.History
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Star
import androidx.compose.ui.graphics.vector.ImageVector

enum class NavScreens(
    val title: String,
    val icon: ImageVector,
) {
    Categories(title = "Categories", icon = Icons.Rounded.Home),
    Favorites(title = "Favorites", icon = Icons.Rounded.Star),
    LastSeen(title = "Last seen", icon = Icons.Rounded.History);
}