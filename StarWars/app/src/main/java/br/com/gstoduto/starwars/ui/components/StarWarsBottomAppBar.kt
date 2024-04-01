package br.com.gstoduto.starwars.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import br.com.gstoduto.starwars.ui.theme.StarWarsTheme
import br.com.gstoduto.starwars.ui.theme.unselectedIconBottomAppBarColor
import br.com.gstoduto.starwars.ui.theme.unselectedTextBottomAppBarColor


sealed class BottomAppBarItem(
    val icon: ImageVector,
    val label: String,
    val category: String
) {
    object Movie : BottomAppBarItem(
        Icons.Default.Movie,
        "Films",
        "movie.myMovieList.movieDetails"
    )

    object Vehicle : BottomAppBarItem(
        Icons.Default.DirectionsCar,
        "Vehicles",
        "vehicle.myVehicleList.vehicleDetails"
    )

    object Specie : BottomAppBarItem(
        Icons.Default.Android,
        "Species",
        "specie.mySpecieList.specieDetails"
    )

    object MyMovieList : BottomAppBarItem(
        Icons.Default.List,
        "Favorite movies",
        "movie.myMovieList.movieDetails"
    )

    object MyVehicleList : BottomAppBarItem(
        Icons.Default.List,
        "Favorite vehicles",
        "vehicle.myVehicleList.vehicleDetails"
    )

    object MySpecieList : BottomAppBarItem(
        Icons.Default.List,
        "Favorite species",
        "specie.mySpecieList.specieDetails"
    )
}

@Composable
fun StarWarsBottomAppBar(
    selectedItem: BottomAppBarItem,
    items: List<BottomAppBarItem>,
    modifier: Modifier = Modifier,
    onItemClick: (BottomAppBarItem) -> Unit = {},
) {
    BottomAppBar(
        modifier,
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        items.forEach { item ->
            if (item.category.contains(selectedItem.category)) {
                NavigationBarItem(
                    selected = item == selectedItem,
                    onClick = { onItemClick(item) },
                    icon = {
                        Column {
                            Icon(
                                item.icon,
                                contentDescription = null
                            )
                        }
                    },
                    label = {
                        Text(text = item.label)
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = MaterialTheme.colorScheme.onBackground,
                        selectedTextColor = MaterialTheme.colorScheme.onBackground,
                        unselectedIconColor = unselectedIconBottomAppBarColor,
                        unselectedTextColor = unselectedTextBottomAppBarColor
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun StarWarsBottomAppBarPreview() {
    StarWarsTheme {
        Surface {
            StarWarsBottomAppBar(
                selectedItem = BottomAppBarItem.Movie,
                items = listOf(
                    BottomAppBarItem.Movie,
                    BottomAppBarItem.Vehicle,
                    BottomAppBarItem.Specie,
                    BottomAppBarItem.MyMovieList,
                    BottomAppBarItem.MyVehicleList,
                    BottomAppBarItem.MySpecieList,
                )
            )
        }
    }
}