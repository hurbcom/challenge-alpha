package br.com.mdr.starwars.ui.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.com.mdr.starwars.navigation.RootNavGraph
import br.com.mdr.starwars.ui.presentation.components.SpaceBackgroundView
import br.com.mdr.starwars.ui.theme.StarWarsTheme

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StarWarsTheme {
                navController = rememberNavController()
                SpaceBackgroundView {
                    RootNavGraph(navController = navController)
                }
            }
        }
    }
}