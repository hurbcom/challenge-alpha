package br.com.gstoduto.starwars.ui.screens.specie

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import br.com.gstoduto.starwars.model.Specie
import br.com.gstoduto.starwars.sampleData.sampleSpecies
import br.com.gstoduto.starwars.ui.components.getIndexFromUrl
import br.com.gstoduto.starwars.ui.theme.StarWarsTheme
import br.com.gstoduto.starwars.ui.uistates.specie.MySpecieListUiState
import br.com.gstoduto.starwars.util.Constants
import coil.compose.AsyncImage

@Composable
fun MySpecieListScreen(
    uiState: MySpecieListUiState,
    onSeeOtherSpecies: () -> Unit,
    onSpecieClick: (Specie) -> Unit,
    onRemoveSpecieFromMyList: (Specie) -> Unit,
    refreshKey: Int,
    modifier: Modifier = Modifier
) {
    key(refreshKey) {
        when (uiState) {
            is MySpecieListUiState.Loading -> {
                Box(Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        Modifier.align(Alignment.Center)
                    )
                }
            }

            is MySpecieListUiState.Success -> {
                val species = uiState.species
                val size = species.size
                val columns = remember(size) {
                    when {
                        size < 4 -> 1
                        size < 6 -> 2
                        else -> 3
                    }
                }
                Column {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(columns),
                        modifier
                            .fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(species) { specie ->
                            Column(
                                Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .clickable {
                                        onSpecieClick(specie)
                                    },
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = specie.name,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Box {
                                    Box(
                                        Modifier
                                            .padding(8.dp)
                                            .background(
                                                color = Color.Black.copy(alpha = 0.5f),
                                                shape = CircleShape
                                            )
                                            .clip(CircleShape)
                                            .align(Alignment.TopEnd)
                                            .clickable {
                                                onRemoveSpecieFromMyList(specie)
                                            }
                                            .padding(4.dp)
                                    ) {
                                        Icon(
                                            Icons.Default.Close,
                                            contentDescription = null,
                                            Modifier.align(
                                                Alignment.Center
                                            )
                                        )
                                    }
                                    val urlImage = getIndexFromUrl(specie.url, "/species/")
                                    val image = "${Constants.BASE_URL_SPECIE_IMAGE}$urlImage.jpg"

                                    AsyncImage(
                                        model = image,
                                        contentDescription = null,
                                        Modifier
                                            .fillMaxWidth()
                                            .clip(RoundedCornerShape(8.dp))
                                            .zIndex(-1f),
                                        placeholder = ColorPainter(Color.Gray),
                                        contentScale = ContentScale.Crop
                                    )
                                }
                            }
                        }
                    }

                }
            }

            is MySpecieListUiState.Empty -> {
                Box(
                    Modifier.fillMaxSize()
                ) {
                    Column(
                        Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Sem espécies na sua lista",
                            style = MaterialTheme.typography.titleLarge
                        )
                        TextButton(onClick = onSeeOtherSpecies) {
                            Text(text = "Adicionar novas espécies")
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MySpecieListScreenPreview() {
    StarWarsTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            MySpecieListScreen(
                uiState = MySpecieListUiState.Success(sampleSpecies),
                onSeeOtherSpecies = {},
                onRemoveSpecieFromMyList = {},
                onSpecieClick = {},
                refreshKey= 0
            )
        }
    }
}

@Preview
@Composable
fun MySpecieListScreenWithoutSpeciesPreview() {
    StarWarsTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            MySpecieListScreen(
                uiState = MySpecieListUiState.Empty(),
                onSeeOtherSpecies = {},
                onRemoveSpecieFromMyList = {},
                onSpecieClick = {},
                refreshKey = 0
            )
        }
    }
}

