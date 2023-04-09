package br.com.hurbandroidchallenge.presentation.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.hurbandroidchallenge.presentation.compose.components.ItemCard
import br.com.hurbandroidchallenge.presentation.compose.components.state.error.DefaultErrorScreen
import br.com.hurbandroidchallenge.presentation.compose.components.state.loading.DefaultLoadingScreen
import br.com.hurbandroidchallenge.presentation.model.StateUI

@Composable
fun HomeMainScreen(
    navHostController: NavHostController,
    viewModel: HomeListViewModel,

    ) {
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize()
        ) {
            viewModel.homeCategories.collectAsState().value.let { response ->
                when (response) {
                    is StateUI.Error -> DefaultErrorScreen(message = response.message)
                    is StateUI.Idle -> Unit
                    is StateUI.Processed -> HomeCategoriesScreen(viewModel = viewModel)
                    is StateUI.Processing -> DefaultLoadingScreen()
                }
            }
        }
    }
}

@Composable
fun HomeCategoriesScreen(viewModel: HomeListViewModel) {
    val homeUI = viewModel.homeUI.value
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(homeUI.categories) { category ->
            ItemCard(
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(id = category.nameRes),
                image = category.imageUrl,
                aspectRadio = 18f / 9f
            )
        }
    }
}