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
import com.example.challengealphaandroid.model.Starship

class DetailStarshipActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val starship = intent.getParcelableExtra("STARSHIP") as Starship?
        setContent {
            if (starship != null) {
                StarshipDetailScreen(starship = starship)
            }
        }
    }
}

@Composable
fun StarshipDetailScreen(starship: Starship) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = starship.name) },
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
                    BuildConfig.RESOURCES + "starships/" + starship.id.toString() + ".jpg",
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
                    text = starship.name,
                    style = MaterialTheme.typography.h4,
                    color = Color.Yellow
                )
                Spacer(modifier = Modifier.height(8.dp))
                if (starship.model != null) {
                    Text(
                        text = "Model: ${starship.model}",
                        style = MaterialTheme.typography.h6,
                        color = Color.Yellow
                    )
                }
                if (starship.starshipClass != null) {
                    Text(
                        text = "Class: ${starship.starshipClass}",
                        style = MaterialTheme.typography.h6,
                        color = Color.Yellow
                    )
                }
            }
        }
    }
}