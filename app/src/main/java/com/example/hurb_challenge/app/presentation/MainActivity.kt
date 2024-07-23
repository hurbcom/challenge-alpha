package com.example.hurb_challenge.app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.hurb_challenge.app.core.serializableType
import com.example.hurb_challenge.app.domain.model.Character
import com.example.hurb_challenge.app.domain.model.Film
import com.example.hurb_challenge.app.presentation.characterdetails.CharacterDetailsScreen
import com.example.hurb_challenge.app.presentation.characterdetails.CharacterDetailsViewModel
import com.example.hurb_challenge.app.presentation.theme.HurbChallengeTheme
import com.example.hurb_challenge.app.presentation.home.CharactersViewModel
import com.example.hurb_challenge.app.presentation.common.BottomNavItem
import com.example.hurb_challenge.app.presentation.common.Destination
import com.example.hurb_challenge.app.presentation.common.composable.BottomNavigationBar
import com.example.hurb_challenge.app.presentation.filmdetails.FilmDetailsScreen
import com.example.hurb_challenge.app.presentation.filmdetails.FilmDetailsViewModel
import com.example.hurb_challenge.app.presentation.home.HomeSectionScreen
import com.example.hurb_challenge.app.presentation.home.films.FilmsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.reflect.typeOf

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        setContent {
            HurbChallengeTheme {
                val navBarItems = listOf(BottomNavItem.Movies, BottomNavItem.Characters)
                val navController = rememberNavController()

                BackHandler {
                    navController.popBackStack()
                }

                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(navBarItems, navController)
                    }
                ) { innerPadding ->
                    NavHost(navController = navController, startDestination = Destination.Films) {
                        composable<Destination.Films> {
                            val viewModel: FilmsViewModel = hiltViewModel()
                            val cardItem = viewModel.cardItemStateFlow.collectAsLazyPagingItems()
                            HomeSectionScreen(
                                cardItem = cardItem,
                                onRetry = { viewModel.loadCardItems() },
                                onItemClicked = { film ->
                                    navController.navigate(
                                        Destination.FilmDetails(film)
                                    ) },
                                modifier = Modifier
                                    .padding(innerPadding)
                                    .fillMaxSize()
                            )
                        }

                        composable<Destination.Characters> {
                            val viewModel: CharactersViewModel = hiltViewModel()
                            val cardItem = viewModel.cardItemStateFlow.collectAsLazyPagingItems()
                            HomeSectionScreen(
                                cardItem = cardItem,
                                onRetry = { viewModel.loadCardItems() },
                                onItemClicked = { character ->
                                    navController.navigate(
                                        Destination.CharactersDetails(character)
                                    ) },
                                modifier = Modifier
                                    .padding(innerPadding)
                                    .fillMaxSize()
                            )
                        }

                        composable<Destination.FilmDetails>(
                            typeMap = mapOf(typeOf<Film>() to serializableType<Film>())
                        ) {
                            val film = it.toRoute<Destination.FilmDetails>().film
                            val viewModel: FilmDetailsViewModel = hiltViewModel()
                            FilmDetailsScreen(
                                film = film,
                                viewModel = viewModel,
                                modifier = Modifier
                                    .padding(innerPadding)
                                    .fillMaxSize(),
                                onRetry = { viewModel.loadCharactersByUrl(film.charactersUrl) }
                            )
                        }

                        composable<Destination.CharactersDetails>(
                            typeMap = mapOf(typeOf<Character>() to serializableType<Character>())
                        ) {
                            val character = it.toRoute<Destination.CharactersDetails>().character
                            val viewModel: CharacterDetailsViewModel = hiltViewModel()
                            CharacterDetailsScreen(
                                character = character,
                                viewModel = viewModel,
                                modifier = Modifier
                                    .padding(innerPadding)
                                    .fillMaxSize()
                            )
                        }
                    }
                }
            }
        }
    }
}
