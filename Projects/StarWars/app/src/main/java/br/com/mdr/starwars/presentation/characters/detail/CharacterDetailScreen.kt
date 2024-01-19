package br.com.mdr.starwars.presentation.characters.detail

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavHostController
import br.com.mdr.starwars.presentation.components.DetailScreenTopBar
import br.com.mdr.starwars.presentation.components.EmptyScreen
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(navController: NavHostController) {

    val viewModel: CharacterDetailViewModel = koinViewModel()
    val character by viewModel.character.collectAsState(null)

    val isFavorite by viewModel.isFavorite.collectAsState()

    val scrollBehavior = TopAppBarDefaults
        .enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = Color.Black,
        topBar = {
            DetailScreenTopBar(
                scrollBehavior = scrollBehavior,
                navController = navController,
                detailViewModel = viewModel,
                isFavorite = isFavorite
            )
        }
    ) {
        character?.let{
            CharacterDetailContent(character = it)
        } ?: EmptyScreen(viewModel = viewModel)
    }
}