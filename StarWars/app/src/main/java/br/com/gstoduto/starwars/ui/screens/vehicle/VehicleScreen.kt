package br.com.gstoduto.starwars.ui.screens.vehicle

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.gstoduto.starwars.R
import br.com.gstoduto.starwars.model.Vehicle
import br.com.gstoduto.starwars.sampleData.sampleVehicles
import br.com.gstoduto.starwars.ui.components.getIndexFromUrl
import br.com.gstoduto.starwars.ui.theme.StarWarsTheme
import br.com.gstoduto.starwars.ui.uistates.vehicle.VehicleUiState
import br.com.gstoduto.starwars.util.Constants
import coil.compose.AsyncImage

@Composable
fun VehicleScreen(
    uiState: VehicleUiState,
    onVehicleClick: (Vehicle) -> Unit,
    onRetryLoadVehicles: () -> Unit,
    columns: Int,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        VehicleUiState.Loading -> {
            Box(Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    Modifier.align(Alignment.Center)
                )
            }
        }

        is VehicleUiState.Success -> {
            val vehicles = uiState.vehicles
            Column(
                modifier
                    .fillMaxSize()
                    .background(color = Color.Black)
            ) {
                Surface {
                    Column(
                        modifier
                            .background(color = Color.Black)
                    ) {
                        Image(
                            painterResource(id = R.drawable.star_wars),
                            contentDescription = null,
                            Modifier
                                .fillMaxWidth(),
                            contentScale = ContentScale.Crop
                        )
                        Text(
                            text = "H o m e / V e h i c l e s",
                            Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp),
                            fontSize = 12.sp,
                            textAlign = TextAlign.Start
                        )
                    }
                }
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(columns),
                    Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalItemSpacing = 16.dp,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(vehicles) { vehicle ->
                        val urlImage = getIndexFromUrl(vehicle.url, "/vehicles/")
                        val image = "${Constants.BASE_URL_VEHICLE_IMAGE}$urlImage.jpg"

                        Column {
                            AsyncImage(
                                model = image,
                                contentDescription = null,
                                Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .clickable {
                                        onVehicleClick(vehicle)
                                    },
                                placeholder = ColorPainter(Color.Gray),
                                contentScale = ContentScale.Crop
                            )
                            Text(
                                text = vehicle.name,
                                Modifier
                                    .background(color = Color.White)
                                    .fillMaxWidth()
                                    .padding(start = 4.dp),
                                style = TextStyle.Default.copy(
                                    fontSize = 10.sp,
                                    color = Color.Black
                                )
                            )
                        }
                    }
                }
            }
        }

        is VehicleUiState.Empty -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Nenhum veiculo encontrado!",
                    style = TextStyle.Default.copy(
                        fontSize = MaterialTheme.typography.titleLarge.fontSize
                    )
                )
                TextButton(onClick = { onRetryLoadVehicles() }) {
                    Text(text = "Tentar buscar novamente")
                }
            }
        }
    }
}

@Preview
@Composable
fun VehicleScreenPreview() {
    StarWarsTheme {
        Surface {
            Surface(color = MaterialTheme.colorScheme.background) {
                VehicleScreen(
                    uiState = VehicleUiState.Success(
                        vehicles = sampleVehicles
                    ),
                    onRetryLoadVehicles = {},
                    onVehicleClick = {},
                    columns = 2
                )
            }
        }
    }
}