package com.vdemelo.starwarswiki.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vdemelo.starwarswiki.R
import com.vdemelo.starwarswiki.ui.species.SpeciesListScreen
import com.vdemelo.starwarswiki.ui.theme.ComposeStarWarsTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: HomeViewModel by viewModel() //TODO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeStarWarsTheme {
                val navController = rememberNavController()
                val navDestSpeciesList = stringResource(id = R.string.nav_dest_species_list)

                NavHost(
                    navController = navController,
                    startDestination = navDestSpeciesList
                ) {
                    composable(route = navDestSpeciesList) {
                        SpeciesListScreen(navController = navController)
                    }
                }

            }
        }
    }

}
