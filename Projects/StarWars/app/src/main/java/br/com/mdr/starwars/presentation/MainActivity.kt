package br.com.mdr.starwars.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.com.mdr.starwars.navigation.RootNavGraph
import br.com.mdr.starwars.presentation.components.SpaceView
import br.com.mdr.starwars.ui.theme.StarWarsTheme

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StarWarsTheme {
                navController = rememberNavController()
                ScreenBackground {
                    RootNavGraph(navController = navController)
                }
            }
        }
    }
}

@Composable
fun ScreenBackground(content: @Composable () -> Unit ) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        SpaceView()
        content()
    }
}