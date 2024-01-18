package br.com.mdr.starwars.presentation.categories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import br.com.mdr.starwars.domain.model.Category
import br.com.mdr.starwars.domain.model.PageState
import br.com.mdr.starwars.navigation.Screen
import br.com.mdr.starwars.presentation.common.EmptyScreen
import br.com.mdr.starwars.presentation.components.ShimmerEffect
import br.com.mdr.starwars.ui.theme.Dimens.MEDIUM_PADDING
import org.koin.androidx.compose.koinViewModel
import timber.log.Timber

@Composable
fun CategoriesScreen(navController: NavHostController) {
    val viewModel: CategoriesViewModel = koinViewModel<CategoriesViewModel>()
    val pageState = viewModel.categories.collectAsState()

    LaunchedEffect(key1 = null, block = {
        viewModel.getCategories()
    })

    HandlePagingResult(
        pageState = pageState.value,
        navController = navController,
        viewModel = viewModel
    )
}

@Composable
fun HandlePagingResult(
    pageState: PageState<List<Category>>,
    navController: NavHostController,
    viewModel: CategoriesViewModel
) {
    when (pageState) {
        is PageState.Empty -> { EmptyScreen(viewModel = viewModel) }
        is PageState.Error -> { EmptyScreen(viewModel = viewModel, error = pageState) }
        is PageState.Loading -> { ShimmerEffect() }
        is PageState.Success -> {
            ListContent(categories = pageState.result, navController = navController)
        }
    }
}


@Composable
fun ListContent(categories: List<Category>, navController: NavHostController) {
    LazyColumn(
        contentPadding = PaddingValues(MEDIUM_PADDING),
        verticalArrangement = Arrangement.spacedBy(MEDIUM_PADDING)
    ) {
        items(count = categories.size) { index ->
            CategoryItem(
                category = categories[index]
            ) { category ->
                handleCategoryClick(
                    category = category,
                    navController = navController
                )
            }
        }
    }
}

private const val FILMS_CATEGORY = "films"
private const val CHARACTERS_CATEGORY = "people"
private fun handleCategoryClick(category: Category, navController: NavHostController) {
    when (category.name) {
        FILMS_CATEGORY -> { navController.navigate(Screen.Films.route) }
        CHARACTERS_CATEGORY -> {}
        else -> { 
            Timber.d("Category not implemented")
        }
    }
}