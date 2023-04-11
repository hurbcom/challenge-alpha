package br.com.hurbandroidchallenge.presentation.screens.home

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.hurbandroidchallenge.R
import br.com.hurbandroidchallenge.presentation.compose.components.ItemCard
import br.com.hurbandroidchallenge.presentation.compose.components.divider.DefaultDivider
import br.com.hurbandroidchallenge.presentation.compose.components.state.error.DefaultErrorScreen
import br.com.hurbandroidchallenge.presentation.compose.components.state.loading.DefaultLoadingScreen
import br.com.hurbandroidchallenge.presentation.model.StateUI
import br.com.hurbandroidchallenge.presentation.compose.components.top_bar.TopBar
import br.com.hurbandroidchallenge.presentation.model.CategoryItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeMainScreen(
    navHostController: NavHostController,
    viewModel: HomeListViewModel,
) {
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
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { paddingValues ->
        val homeUI = viewModel.homeUI.value
        Column(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize()
        ) {
            viewModel.homeCategories.collectAsState().value.let { response ->
                when (response) {
                    is StateUI.Error -> DefaultErrorScreen(message = response.message)
                    is StateUI.Idle -> Unit
                    is StateUI.Processed -> ItemList(
                        categoryItems = homeUI.categories.map { category ->
                            CategoryItem(
                                text = category.name,
                                aspectRatio = 2f / 1f,
                                image = category.image
                            )
                        }
                    )
                    is StateUI.Processing -> DefaultLoadingScreen()
                }
            }
        }
    }
}

@Composable
fun ItemList(categoryItems: List<CategoryItem>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(categoryItems) { category ->
            ItemCard(
                modifier = Modifier.fillMaxWidth(),
                title = category.text,
                image = category.image,
                aspectRadio = 18f / 9f
            )
        }
    }
}