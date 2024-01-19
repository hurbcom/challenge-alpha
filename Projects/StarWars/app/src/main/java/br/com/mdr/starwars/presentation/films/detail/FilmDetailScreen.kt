package br.com.mdr.starwars.presentation.films.detail

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import br.com.mdr.starwars.R
import br.com.mdr.starwars.presentation.characters.detail.CharacterDetailViewModel
import br.com.mdr.starwars.presentation.components.EmptyScreen
import br.com.mdr.starwars.ui.theme.ThemeColor
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmDetailScreen(navController: NavHostController) {

    val viewModel: CharacterDetailViewModel = koinViewModel()
    val film by viewModel.film.collectAsState(null)

    val isFavorite by viewModel.isFavorite.collectAsState()

    val scrollBehavior = TopAppBarDefaults
        .enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = Color.Black,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                ),
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.favorite_icon),
                            tint = ThemeColor
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            viewModel.setFavorite()
                        }
                    ) {
                        Icon(
                            painter = painterResource(
                                id = if (isFavorite) {
                                    R.drawable.ic_favorite_filled
                                } else {
                                    R.drawable.ic_favorite
                                }
                            ),
                            contentDescription = stringResource(id = R.string.favorite_icon),
                            tint = ThemeColor
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) {
        film?.let{
            FilmDetailItem(film = it)
        } ?: EmptyScreen(viewModel = viewModel)
    }
}