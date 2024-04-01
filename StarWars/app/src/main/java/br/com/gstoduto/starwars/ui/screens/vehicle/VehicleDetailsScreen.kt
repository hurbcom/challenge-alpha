package br.com.gstoduto.starwars.ui.screens.vehicle

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
import br.com.gstoduto.starwars.model.Vehicle
import br.com.gstoduto.starwars.sampleData.sampleVehicleAdded
import br.com.gstoduto.starwars.sampleData.sampleVehicleRemoved
import br.com.gstoduto.starwars.ui.components.getIndexFromUrl
import br.com.gstoduto.starwars.ui.theme.StarWarsTheme
import br.com.gstoduto.starwars.ui.uistates.vehicle.VehicleDetailsUiState
import br.com.gstoduto.starwars.util.Constants
import coil.compose.AsyncImage

private class ToggleButton(
    val text: String,
    val icon: ImageVector,
    val action: (Vehicle) -> Unit,
    val buttonColor: Long
)

@Composable
fun VehicleDetailsScreen(
    uiState: VehicleDetailsUiState,
    onAddVehicleToMyListClick: (Vehicle) -> Unit,
    onRemoveVehicleFromMyList: (Vehicle) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .fillMaxSize()
            .background(Color(0xFF2B2B2B))
    ) {

        val vehicle = uiState.vehicle
        Column {
            Column(
                Modifier
                    .padding(horizontal = 24.dp, vertical = 36.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    vehicle?.let {
                        val urlImage = getIndexFromUrl(vehicle.url, "/vehicles/")
                        val image = "${Constants.BASE_URL_VEHICLE_IMAGE}$urlImage.jpg"

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
                                text = vehicle.name,
                                style = TextStyle.Default.copy(
                                    fontSize = 16.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                ))
                            Spacer(modifier = Modifier.size(8.dp))
                            Text(text = "Model: ${vehicle.model}")
                            Spacer(modifier = Modifier.size(4.dp))
                            Text(text = "Manufacturer: ${vehicle.manufacturer}")
                            Spacer(modifier = Modifier.size(4.dp))
                            Text(text = "Class: ${vehicle.vehicleClass}")
                            Spacer(modifier = Modifier.size(4.dp))
                            Text(text = "Cost: ${vehicle.costInCredits} credits")
                            Spacer(modifier = Modifier.size(4.dp))
                            Text(text = "Speed: ${vehicle.maxAtmospheringSpeed} km/h")
                            Spacer(modifier = Modifier.size(4.dp))
                            Text(text = "Length: ${vehicle.length}m")
                            Spacer(modifier = Modifier.size(4.dp))
                            Text(text = "Cargo Capacity: ${vehicle.cargoCapacity}")
                            Spacer(modifier = Modifier.size(4.dp))
                            Text(text = "Mimimum Crew: ${vehicle.consumables}")
                            Spacer(modifier = Modifier.size(4.dp))
                            Text(text = "Passengers: ${vehicle.passengers}")
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
                        val toggleButton = if (vehicle?.inMyList == true) {
                            ToggleButton(
                                icon = Icons.Default.PlaylistRemove,
                                text = "Remover da lista",
                                action = onRemoveVehicleFromMyList,
                                buttonColor = 0xffB20510
                            )
                        } else {
                            ToggleButton(
                                icon = Icons.Default.PlaylistAdd,
                                text = "Adicionar à lista",
                                action = onAddVehicleToMyListClick,
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
                                        vehicle?.let { toggleButton.action(it) }
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
fun VehicleDetailsScreenWithMovieAddedToMyListPreview() {
    StarWarsTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            VehicleDetailsScreen(
                uiState = VehicleDetailsUiState(),
                onAddVehicleToMyListClick = {},
                onRemoveVehicleFromMyList = {},
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VehicleDetailsScreenWithoutMovieAddedToMyListPreview() {
    StarWarsTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            VehicleDetailsScreen(
                uiState = VehicleDetailsUiState(vehicle = sampleVehicleAdded),
                onAddVehicleToMyListClick = {},
                onRemoveVehicleFromMyList = {},
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VehicleDetailsScreenWithMovieRemovedFromMyListPreview() {
    StarWarsTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            VehicleDetailsScreen(
                uiState = VehicleDetailsUiState(vehicle = sampleVehicleRemoved),
                onAddVehicleToMyListClick = {},
                onRemoveVehicleFromMyList = {},
            )
        }
    }
}
