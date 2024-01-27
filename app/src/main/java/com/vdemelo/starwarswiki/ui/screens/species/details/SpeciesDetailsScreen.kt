package com.vdemelo.starwarswiki.ui.screens.species.details

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
fun SpeciesDetailsScreen(
    navController: NavController,
    id: Int?,
    viewModel: SpeciesDetailsViewModel = getViewModel()
) {
    val species by remember { viewModel.speciesDetails }
    val loadError by remember { viewModel.loadError }
    val isLoading by remember { viewModel.isLoading }

    if (id == null) {
        ErrorScreen(
            error = stringResource(id = R.string.common_unknown_error),
            buttonLabel = stringResource(id = R.string.common_back)
        ) {
            navController.navigate(route = NavItem.SpeciesList.route)
        }
    } else {
        viewModel.loadSpeciesDetails(id)
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            if (isLoading) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            } else if (species != null) {
                val imageUrl = viewModel.getSpeciesImageUrl(id)
                DetailsScreen(
                    title = stringResource(id = R.string.details_screen_title_species),
                    imageUrl = imageUrl,
                    fields = listOf<TextField>(
                        TextField(
                            label = stringResource(id = R.string.list_screen_name_label),
                            text = species!!.name
                        ),
                        TextField(
                            label = stringResource(id = R.string.list_screen_language_label),
                            text = species!!.language
                        ),
                        TextField(
                            label = stringResource(id = R.string.list_screen_classification_label),
                            text = species!!.classification
                        ),
                        TextField(
                            label = stringResource(id = R.string.list_screen_average_height_label),
                            text = species!!.averageHeight
                        ),
                        TextField(
                            label = stringResource(id = R.string.list_screen_average_lifespan_label),
                            text = species!!.averageLifespan
                        )
                    )
                )
            } else {
                RetrySection(
                    error = loadError,
                    onRetry = {
                        viewModel.loadSpeciesDetails(id)
                    }
                )
            }
        }
    }
}
