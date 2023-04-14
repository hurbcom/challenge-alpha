package br.com.hurbandroidchallenge.presentation.screens.home.categories

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import br.com.hurbandroidchallenge.presentation.compose.widgets.state.error.DefaultErrorScreen
import br.com.hurbandroidchallenge.presentation.compose.widgets.state.loading.DefaultLoadingScreen
import br.com.hurbandroidchallenge.presentation.model.StateUI
import br.com.hurbandroidchallenge.presentation.screens.home.categories.components.CategoryItemList
import org.koin.androidx.compose.getViewModel

@Composable
fun CategoriesMainScreen(
    viewModel: CategoriesViewModel = getViewModel(),
    navHostController: NavHostController,
) {
    val homeUI = viewModel.homeUI.value
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