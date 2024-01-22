package br.com.mdr.starwars.ui.presentation.categories

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.navigation.NavHostController
import br.com.mdr.starwars.domain.model.Category
import br.com.mdr.starwars.navigation.Screen
import br.com.mdr.starwars.ui.presentation.components.HandlePageStateResult
import br.com.mdr.starwars.ui.theme.Dimens.MEDIUM_PADDING
import org.koin.androidx.compose.koinViewModel

@Suppress("UNCHECKED_CAST")
@Composable
fun CategoriesScreen(navController: NavHostController) {
    val viewModel: CategoriesViewModel = koinViewModel<CategoriesViewModel>()
    val pageState = viewModel.categories.collectAsState()

    LaunchedEffect(key1 = null, block = {
        viewModel.getCategories()
    })

    HandlePageStateResult(
        pageState = pageState.value,
        viewModel = viewModel
    ) {
        ListContent(
            categories = it.result as? List<Category> ?: listOf(),
            navController = navController
        )
    }
}

@Composable
private fun ListContent(categories: List<Category>, navController: NavHostController) {
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .semantics {
                contentDescription = "Lista categorias"
            },
        contentPadding = PaddingValues(MEDIUM_PADDING),
        verticalArrangement = Arrangement.spacedBy(MEDIUM_PADDING)
    ) {
        items(count = categories.size) { index ->
            CategoryItem(
                category = categories[index]
            ) { category ->
                handleCategoryClick(
                    category = category,
                    navController = navController,
                    context = context
                )
            }
        }
    }
}

private const val FILMS_CATEGORY = "films"
private const val CHARACTERS_CATEGORY = "people"
private fun handleCategoryClick(category: Category, navController: NavHostController,
                                context: Context) {
    when (category.name) {
        FILMS_CATEGORY -> { navController.navigate(Screen.Films.route) }
        CHARACTERS_CATEGORY -> { navController.navigate(Screen.Characters.route) }
        else -> {
            Toast.makeText(context, "Not implemented yet! =D", Toast.LENGTH_LONG).show()
        }
    }
}