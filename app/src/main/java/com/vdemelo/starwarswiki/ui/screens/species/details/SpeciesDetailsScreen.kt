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
import com.vdemelo.starwarswiki.ui.screens.base.DetailsScreen
import com.vdemelo.starwarswiki.ui.screens.species.list.RetrySection
import org.koin.androidx.compose.getViewModel

@Composable
fun SpeciesDetailsScreen(
    navController: NavController,
    number: Int?,
    viewModel: SpeciesDetailsViewModel = getViewModel()
) {
    val species by remember { viewModel.speciesDetails }
    val loadError by remember { viewModel.loadError }
    val isLoading by remember { viewModel.isLoading }

    if (number == null) {
        //TODO criar uma tela de erro mandando voltar pra tela anterior "algo de errado aconteceu, voltar"
    } else {
        viewModel.loadSpeciesDetails(number)
        Box( //TODO fazer estado de loading e de erro
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            if (isLoading) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            } else if (species != null) {
                val imageUrl = viewModel.getSpeciesImageUrl(number)
                DetailsScreen(
                    title = stringResource(id = R.string.details_screen_title_species),
                    imageUrl = imageUrl,
                    firstField = species!!.name,
                    secondField = species!!.language,
                    thirdField = species!!.classification
                )
            } else {
                RetrySection(
                    error = loadError,
                    onRetry = {
                        viewModel.loadSpeciesDetails(number)
                    }
                )
            }
        }
    }
}
