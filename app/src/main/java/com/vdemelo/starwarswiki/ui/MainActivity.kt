package com.vdemelo.starwarswiki.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.vdemelo.starwarswiki.ui.nav.AppNavHost
import com.vdemelo.starwarswiki.ui.nav.NavigationItem
import com.vdemelo.starwarswiki.ui.theme.ComposeStarWarsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeStarWarsTheme {
                val navController = rememberNavController()

                AppNavHost(
                    navController = navController,
                    startDestination = NavigationItem.SpeciesList.route
                )

            }
        }
    }

}
