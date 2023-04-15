package br.com.hurbandroidchallenge.presentation.compose.components.bottom_navigation

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import br.com.hurbandroidchallenge.presentation.compose.navigation.NavScreens

@Composable
fun DefaultNavigationBar(
    navigationBarItems: List<NavScreens>,
    onItemClick: (NavScreens) -> Unit,
    isSelected: (NavScreens) -> Boolean
) {
    NavigationBar {
        navigationBarItems.forEach { item ->
            NavigationBarItem(
                selected = isSelected(item),
                label = {
                    Text(text = item.title, style = MaterialTheme.typography.labelMedium)
                },
                icon = {
                    Icon(imageVector = item.icon, contentDescription = null)
                },
                onClick = {
                    onItemClick(item)
                }
            )
        }
    }
}