package com.example.challengealphaandroid.ui.detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.challengealphaandroid.BuildConfig
import com.example.challengealphaandroid.R
import com.example.challengealphaandroid.model.Planet

class DetailPlanetActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val planet = intent.getParcelableExtra("PLANET") as Planet?
        setContent {
            if (planet != null) {
                PlanetDetailScreen(planet = planet)
            }
        }
    }
}

@Composable
fun PlanetDetailScreen(planet: Planet) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = planet.name) },
                backgroundColor = MaterialTheme.colors.primary
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            Image(
                painter = rememberImagePainter(
                    BuildConfig.RESOURCES + "planets/" + planet.id.toString() + ".jpg",
                    builder = {
                        error(R.drawable.image_not_found_icon)
                    }),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = planet.name,
                    style = MaterialTheme.typography.h4,
                    color = Color.Magenta
                )
                Spacer(modifier = Modifier.height(8.dp))
                if (planet.population != null) {
                    Text(
                        text = "Population: ${planet.population}",
                        style = MaterialTheme.typography.h6,
                        color = Color.Magenta
                    )
                }
                if (planet.diameter != null) {
                    Text(
                        text = "Diameter: ${planet.diameter}",
                        style = MaterialTheme.typography.h6,
                        color = Color.Magenta
                    )
                }
            }
        }
    }
}