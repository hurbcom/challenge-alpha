package br.com.hurbandroidchallenge.presentation.screens.home

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.hurbandroidchallenge.R
import br.com.hurbandroidchallenge.presentation.compose.components.DefaultNavigationBar
import br.com.hurbandroidchallenge.presentation.compose.navigation.NavScreens.*
import br.com.hurbandroidchallenge.presentation.compose.widgets.divider.DefaultDivider
import br.com.hurbandroidchallenge.presentation.compose.widgets.top_bar.TopBar
import br.com.hurbandroidchallenge.presentation.screens.home.categories.CategoriesMainScreen
import br.com.hurbandroidchallenge.presentation.screens.home.favorites.FavoritesMainScreen
import br.com.hurbandroidchallenge.presentation.screens.home.last_seen.LastSeenMainScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeMainScreen(
    navHostController: NavHostController
) {
    val navigationItems = values().toList()
    var currentScreen by rememberSaveable { mutableStateOf(navigationItems.first()) }
    val scrollBehavior =
        TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(
        topBar = {
            TopBar(title = "", scrollBehavior = scrollBehavior) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(modifier = Modifier.padding(vertical = 16.dp)) {
                        Icon(
                            modifier = Modifier.size(30.dp),
                            painter = painterResource(
                                id = if (isSystemInDarkTheme())
                                    R.drawable.ic_star_wars_imperial
                                else
                                    R.drawable.ic_star_wars_rebel
                            ),
                            tint = MaterialTheme.colorScheme.onSurface,
                            contentDescription = null
                        )
                    }
                    DefaultDivider(modifier = Modifier.fillMaxWidth())
                }
            }
        },
        bottomBar = {
            DefaultNavigationBar(
                navigationBarItems = navigationItems,
                onItemClick = { screen ->
                    currentScreen = screen
                },
                isSelected = { screen ->
                    currentScreen == screen
                }
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize()
        ) {
            when(currentScreen) {
                Categories -> CategoriesMainScreen(navHostController = navHostController)
                Favorites -> FavoritesMainScreen(navHostController = navHostController)
                LastSeen -> LastSeenMainScreen(navHostController = navHostController)
            }
        }
    }
}