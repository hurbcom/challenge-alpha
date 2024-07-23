package com.example.hurb_challenge.app.presentation.common.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.hurb_challenge.R
import com.example.hurb_challenge.app.presentation.common.BottomNavItem

@Composable
fun BottomNavigationBar(tabBarItems: List<BottomNavItem>, navController: NavController) {
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }

    NavigationBar(modifier = Modifier.border(BorderStroke(width = 1.dp, Color.DarkGray))) {

        tabBarItems.forEachIndexed { index, navBarItem ->
            val isSelected = selectedTabIndex == index
            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    selectedTabIndex = index
                    navController.navigate(navBarItem.route)
                },
                icon = {
                    val icon = if (isSelected) navBarItem.selectedIcon else navBarItem.unselectedIcon
                    Icon(imageVector = icon, contentDescription = stringResource(id = navBarItem.label))
                },
                label = { Text(text = stringResource(id = navBarItem.label)) }
            )
        }
    }
}
