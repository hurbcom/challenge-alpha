package br.com.mdr.starwars.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.mdr.starwars.navigation.BottomBarScreen
import br.com.mdr.starwars.navigation.HomeNavGraph
import br.com.mdr.starwars.ui.theme.NavigationBarContentColor
import br.com.mdr.starwars.ui.theme.NavigationIndicatorColor
import br.com.mdr.starwars.ui.theme.SelectedItemColor
import br.com.mdr.starwars.ui.theme.UnselectedItemColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navHostController: NavHostController = rememberNavController()) {
    Scaffold(
        contentColor = Color.Red,
        bottomBar = { BottomBar(navHostController) }
    ) { paddingValues ->
        HomeNavGraph(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize(),
            navController = navHostController)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Categories,
        BottomBarScreen.Favorites,
        BottomBarScreen.LastSeen
    )

    val navBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStack?.destination

    val bottomDestination = screens.any { it.route == currentDestination?.route }
    if (bottomDestination) {
        NavigationBar(
            containerColor = NavigationBarContentColor
        ) {
            screens.forEach { screen ->
                BarItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun RowScope.BarItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
    NavigationBarItem(
        label = {
            Text(text = stringResource(id = screen.label))
        },
        selected = isSelected,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        icon = {
            Icon(
                painter = painterResource(
                    screen.icon
                ),
                contentDescription = stringResource(id = screen.label)
            )
        },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = SelectedItemColor,
            unselectedIconColor = UnselectedItemColor,
            indicatorColor = NavigationIndicatorColor,
            selectedTextColor = Color.White
        ))
}