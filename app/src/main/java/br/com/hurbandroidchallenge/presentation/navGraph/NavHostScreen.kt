package br.com.hurbandroidchallenge.presentation.navGraph

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.hurbandroidchallenge.presentation.compose.navigation.*
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavHostScreen() {
    val navHostController = rememberAnimatedNavController()
    AnimatedNavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navHostController,
        startDestination = Screens.Home.route,
        builder = {
            home(navHostController = navHostController)
            characters(navHostController = navHostController)
            characterDetail(navHostController = navHostController)
            films(navHostController = navHostController)
            filmDetail(navHostController = navHostController)
            planets(navHostController = navHostController)
            planetDetail(navHostController = navHostController)
        }
    )
}