package com.vdemelo.starwarswiki.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vdemelo.starwarswiki.domain.entity.model.ItemsImageUrl
import com.vdemelo.starwarswiki.domain.entity.model.getImageUrl
import com.vdemelo.starwarswiki.ui.details.SpeciesDetailsScreen
import com.vdemelo.starwarswiki.ui.theme.ComposeStarWarsTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeStarWarsTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = ""
                ) {
                    composable("species_details") {
                        SpeciesDetailsScreen(
                            navController = navController,
                            imageUrl = ItemsImageUrl.SPECIES.getImageUrl("1"),
                            homeWorld = "Terra",
                            language = "Human Language",
                            classification = "Mammal"
                        )
                    }
                }

//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    SpeciesDetailsScreen(
//                        imageUrl = "https://starwars-visualguide.com/assets/img/species/1.jpg",
//                        homeWorld = "Terra",
//                        language = "Human Language",
//                        classification = "Mammal"
//                    )
//                }
            }
        }
    }

}
