package com.example.hurb_challenge.app.presentation.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.hurb_challenge.R

sealed class BottomNavItem(
    val route: Destination,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val label: Int
) {

    data object Movies : BottomNavItem(
        route = Destination.Films,
        selectedIcon = Icons.Filled.Movie,
        unselectedIcon = Icons.Outlined.Movie,
        label = R.string.bottom_nav_film_label
    )
    data object Characters : BottomNavItem(
        route = Destination.Characters,
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person,
        label = R.string.bottom_nav_characters_label
    )
}
