package br.com.gstoduto.starwars.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import br.com.gstoduto.starwars.util.Constants.ID_MOVIE
import br.com.gstoduto.starwars.util.Constants.ID_SPECIE
import br.com.gstoduto.starwars.util.Constants.ID_VEHICLE

sealed class DestinationsStarWarsApp(val rota: String) {
    object movieRoute : DestinationsStarWarsApp("movie")
    object myMovieListRoute : DestinationsStarWarsApp("myMovieList")
    object myMovieDetails : DestinationsStarWarsApp("movieDetails/{id_movie}")

    object MovieDetails {
        const val rota = "movieDetails"
        const val routeWithArguments = "$rota/{$ID_MOVIE}"
        val arguments = listOf(
            navArgument(ID_MOVIE) {
                defaultValue = ""
                type = NavType.StringType
            }
        )
    }

    object vehicleRoute : DestinationsStarWarsApp("vehicle")
    object myVehicleListRoute : DestinationsStarWarsApp("myVehicleList")
    object myVehicleDetails : DestinationsStarWarsApp("vehicleDetails/{id_vehicle}")

    object VehicleDetails {
        const val rota = "vehicleDetails"
        const val routeWithArguments = "$rota/{$ID_VEHICLE}"
        val arguments = listOf(
            navArgument(ID_VEHICLE) {
                defaultValue = ""
                type = NavType.StringType
            }
        )
    }

    object specieRoute : DestinationsStarWarsApp("specie")
    object mySpecieListRoute : DestinationsStarWarsApp("mySpecieList")
    object mySpecieDetails : DestinationsStarWarsApp("specieDetails/{id_specie}")

    object SpecieDetails {
        const val rota = "specieDetails"
        const val routeWithArguments = "$rota/{$ID_SPECIE}"
        val arguments = listOf(
            navArgument(ID_SPECIE) {
                defaultValue = ""
                type = NavType.StringType
            }
        )
    }
}