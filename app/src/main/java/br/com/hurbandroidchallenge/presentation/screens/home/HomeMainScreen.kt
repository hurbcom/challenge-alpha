package br.com.hurbandroidchallenge.presentation.screens.home

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.hurbandroidchallenge.R
import br.com.hurbandroidchallenge.presentation.screens.home.components.CategoryItemList
import br.com.hurbandroidchallenge.presentation.compose.widgets.divider.DefaultDivider
import br.com.hurbandroidchallenge.presentation.compose.widgets.state.error.DefaultErrorScreen
import br.com.hurbandroidchallenge.presentation.compose.widgets.state.loading.DefaultLoadingScreen
import br.com.hurbandroidchallenge.presentation.compose.widgets.top_bar.TopBar
import br.com.hurbandroidchallenge.presentation.model.StateUI

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeMainScreen(
    navHostController: NavHostController,
    viewModel: HomeListViewModel,
) {
    Scaffold(
        topBar = {
            TopBar(title = "") {
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
        }
    ) { paddingValues ->
        val homeUI = viewModel.homeUI.value
        Box(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize()
        ) {
            viewModel.homeCategories.collectAsState().value.let { response ->
                when (response) {
                    is StateUI.Error -> DefaultErrorScreen(message = response.message)
                    is StateUI.Idle -> Unit
                    is StateUI.Processed -> CategoryItemList(
                        categoryItems = homeUI.categories,
                        aspectRatio = 2f / 1f,
                        onItemClick = { route ->
                           navHostController.navigate(route)
                        }
                    )
                    is StateUI.Processing -> DefaultLoadingScreen()
                }
            }
        }
    }
}