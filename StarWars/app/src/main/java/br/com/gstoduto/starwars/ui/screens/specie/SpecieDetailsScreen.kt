package br.com.gstoduto.starwars.ui.screens.specie

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlaylistAdd
import androidx.compose.material.icons.filled.PlaylistRemove
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.gstoduto.starwars.model.Specie
import br.com.gstoduto.starwars.sampleData.sampleSpecieAdded
import br.com.gstoduto.starwars.sampleData.sampleSpecieRemoved
import br.com.gstoduto.starwars.ui.components.getIndexFromUrl
import br.com.gstoduto.starwars.ui.theme.StarWarsTheme
import br.com.gstoduto.starwars.ui.uistates.specie.SpecieDetailsUiState
import br.com.gstoduto.starwars.util.Constants
import coil.compose.AsyncImage

private class ToggleButton(
    val text: String,
    val icon: ImageVector,
    val action: (Specie) -> Unit,
    val buttonColor: Long
)

@Composable
fun SpecieDetailsScreen(
    uiState: SpecieDetailsUiState,
    onAddSpecieToMyListClick: (Specie) -> Unit,
    onRemoveSpecieFromMyList: (Specie) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .fillMaxSize()
            .background(Color(0xFF2B2B2B))
    ) {

        val specie = uiState.specie
        Column {
            Column(
                Modifier
                    .padding(horizontal = 24.dp, vertical = 36.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    specie?.let {
                        val urlImage = getIndexFromUrl(specie.url, "/species/")
                        val image = "${Constants.BASE_URL_SPECIE_IMAGE}$urlImage.jpg"

                        AsyncImage(
                            model = image,
                            contentDescription = null,
                            Modifier
                                .size(
                                    height = 152.dp,
                                    width = 100.dp
                                )
                                .clip(shape = RoundedCornerShape(4.dp)),
                            placeholder = ColorPainter(Color.Gray),
                            contentScale = ContentScale.Crop
                        )
                        Column {
                            Text(
                                text = specie.name,
                                style = TextStyle.Default.copy(
                                    fontSize = 16.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                ))
                            Spacer(modifier = Modifier.size(8.dp))
                            Text(text = "Classification: ${specie.classification}")
                            Spacer(modifier = Modifier.size(4.dp))
                            Text(text = "Designation: ${specie.designation}")
                            Spacer(modifier = Modifier.size(4.dp))
                            Text(text = "Language: ${specie.language}")
                            Spacer(modifier = Modifier.size(4.dp))
                            Text(text = "Avg Lifespan: ${specie.averageLifespan}")
                            Spacer(modifier = Modifier.size(4.dp))
                            Text(text = "Avg Height: ${specie.averageHeight }cm")
                            Spacer(modifier = Modifier.size(4.dp))
                            Text(text = "Hair Color(s): ${specie.hairColors}")
                            Spacer(modifier = Modifier.size(4.dp))
                            Text(text = "Skin Color(s): ${specie.skinColors}")
                            Spacer(modifier = Modifier.size(4.dp))
                            Text(text = "Eye Color(s): ${specie.eyeColors}")
                            Spacer(modifier = Modifier.size(4.dp))
                        }
                    }

                }
                Spacer(modifier = Modifier.size(24.dp))
                Row {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val toggleButton = if (specie?.inMyList == true) {
                            ToggleButton(
                                icon = Icons.Default.PlaylistRemove,
                                text = "Remover da lista",
                                action = onRemoveSpecieFromMyList,
                                buttonColor = 0xffB20510
                            )
                        } else {
                            ToggleButton(
                                icon = Icons.Default.PlaylistAdd,
                                text = "Adicionar à lista",
                                action = onAddSpecieToMyListClick,
                                buttonColor = 0xff4F4F4F
                            )
                        }
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Box(
                                Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth()
                                    .background(
                                        Color(toggleButton.buttonColor),
                                        shape = CircleShape
                                    )
                                    .align(Center)
                                    .clip(CircleShape)
                                    .clickable {
                                        specie?.let { toggleButton.action(it) }
                                    }
                                    .padding(8.dp)
                            ) {
                                Row(
                                    Modifier.align(Center),
                                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        toggleButton.icon,
                                        contentDescription = null,
                                        Modifier
                                            .size(32.dp)
                                    )
                                    Text(text = toggleButton.text)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SpecieDetailsScreenWithMovieAddedToMyListPreview() {
    StarWarsTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            SpecieDetailsScreen(
                uiState = SpecieDetailsUiState(),
                onAddSpecieToMyListClick = {},
                onRemoveSpecieFromMyList = {},
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SpecieDetailsScreenWithoutMovieAddedToMyListPreview() {
    StarWarsTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            SpecieDetailsScreen(
                uiState = SpecieDetailsUiState(specie = sampleSpecieAdded),
                onAddSpecieToMyListClick = {},
                onRemoveSpecieFromMyList = {},
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SpecieDetailsScreenWithMovieRemovedFromMyListPreview() {
    StarWarsTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            SpecieDetailsScreen(
                uiState = SpecieDetailsUiState(specie = sampleSpecieRemoved),
                onAddSpecieToMyListClick = {},
                onRemoveSpecieFromMyList = {},
            )
        }
    }
}
