package br.com.hurbandroidchallenge.presentation.screens.characters

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import br.com.hurbandroidchallenge.R
import br.com.hurbandroidchallenge.data.remote.config.ApiUrls
import br.com.hurbandroidchallenge.domain.model.ItemModel
import br.com.hurbandroidchallenge.presentation.compose.components.lazy_list.PagedItemList
import br.com.hurbandroidchallenge.presentation.compose.widgets.state.error.DefaultErrorScreen
import br.com.hurbandroidchallenge.presentation.compose.widgets.state.loading.DefaultLoadingScreen
import br.com.hurbandroidchallenge.presentation.compose.widgets.top_bar.TopBar
import br.com.hurbandroidchallenge.presentation.model.StateUI

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDetailMainScreen(
    navHostController: NavHostController,
    viewModel: CharactersViewModel,
) {
    val scrollBehavior =
        TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val categoryDetailUI = viewModel.characters.value
    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(id = R.string.home_categories_people),
                onBackPressed = { navHostController.navigateUp() },
                scrollBehavior = scrollBehavior
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize()
        ) {
            val charactersUI = viewModel.characters.value
            val loadMoreResponse = viewModel.loadMoreState.collectAsState().value
            viewModel.charactersState.collectAsState().value.let { response ->
                when (response) {
                    is StateUI.Error -> DefaultErrorScreen(message = response.message)
                    is StateUI.Idle -> Unit
                    is StateUI.Processed -> {
                        PagedItemList(
                            categoryItems = categoryDetailUI.characters.map { character ->
                                ItemModel(
                                    image = "${ApiUrls.imageBaseUrl}characters/${character.id}.jpg",
                                    fields = listOf(
                                        "Nome" to character.name,
                                        "Altura" to character.height,
                                        "Massa" to character.mass
                                    ),
                                    url = character.url
                                )
                            },
                            onItemClick = {

                            },
                            aspectRatio = 4f / 5f,
                            isLoading = loadMoreResponse.loading(),
                            loadMore = {
                                val canLoadMore = !charactersUI.nextPage.isNullOrBlank()
                                if (canLoadMore) {
                                    viewModel.loadMoreCharacters()
                                }
                            }
                        )
                    }
                    is StateUI.Processing -> DefaultLoadingScreen()
                }
            }
        }
    }
}