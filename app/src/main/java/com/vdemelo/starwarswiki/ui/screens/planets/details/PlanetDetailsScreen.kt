package com.vdemelo.starwarswiki.ui.screens.planets.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.vdemelo.starwarswiki.R
import com.vdemelo.starwarswiki.domain.entity.model.TextField
import com.vdemelo.starwarswiki.ui.components.RetrySection
import com.vdemelo.starwarswiki.ui.nav.NavItem
import com.vdemelo.starwarswiki.ui.screens.common.DetailsScreen
import com.vdemelo.starwarswiki.ui.screens.common.ErrorScreen
import org.koin.androidx.compose.getViewModel

@Composable
fun PlanetDetailsScreen(
    navController: NavController,
    id: Int?,
    viewModel: PlanetDetailsViewModel = getViewModel()
) {
    val planet by remember { viewModel.planetDetails }
    val loadError by remember { viewModel.loadError }
    val isLoading by remember { viewModel.isLoading }

    if (id == null) {
        ErrorScreen(
            error = stringResource(id = R.string.common_unknown_error),
            buttonLabel = stringResource(id = R.string.common_back)
        ) {
            navController.navigate(route = NavItem.PlanetsList.route)
        }
    } else {
        viewModel.loadPlanetDetails(id)
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            if (isLoading) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            } else if (planet != null) {
                val imageUrl = viewModel.getPlanetImageUrl(id)
                DetailsScreen(
                    title = stringResource(id = R.string.details_screen_title_planet),
                    imageUrl = imageUrl,
                    fields = listOf<TextField>(
                        TextField(
                            label = stringResource(id = R.string.list_screen_name_label),
                            text = planet!!.name
                        ),
                        TextField(
                            label = stringResource(id = R.string.list_screen_population_label),
                            text = planet!!.population
                        ),
                        TextField(
                            label = stringResource(id = R.string.list_screen_climate_label),
                            text = planet!!.climate
                        ),
                        TextField(
                            label = stringResource(id = R.string.list_screen_diameter_label),
                            text = planet!!.diameter
                        ),
                        TextField(
                            label = stringResource(id = R.string.list_screen_rotation_period_label),
                            text = planet!!.rotationPeriod
                        )
                    )
                )
            } else {
                RetrySection(
                    error = loadError,
                    onRetry = {
                        viewModel.loadPlanetDetails(id)
                    }
                )
            }
        }
    }
}
