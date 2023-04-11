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
import com.example.challengealphaandroid.model.Person

class DetailPersonActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val person = intent.getParcelableExtra("PERSON") as Person?
        setContent {
            if (person != null) {
                PersonDetailScreen(person = person)
            }
        }
    }
}


@Composable
fun PersonDetailScreen(person: Person) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = person.name) },
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
                    BuildConfig.RESOURCES + "characters/" + person.id.toString() + ".jpg",
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
                    text = person.name,
                    style = MaterialTheme.typography.h4,
                    color = Color.Cyan
                )
                Spacer(modifier = Modifier.height(8.dp))
                if (person.birthYear != null) {
                    Text(
                        text = "Birth Year: ${person.birthYear}",
                        style = MaterialTheme.typography.h6,
                        color = Color.Cyan
                    )
                }
                if (person.homeworld.homeName != null) {
                    Text(
                        text = "Homeworld: ${person.homeworld.homeName}",
                        style = MaterialTheme.typography.h6,
                        color = Color.Cyan
                    )
                }
            }
        }
    }
}