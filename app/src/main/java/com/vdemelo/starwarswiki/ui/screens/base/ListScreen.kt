package com.vdemelo.starwarswiki.ui.screens.base

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vdemelo.starwarswiki.R
import com.vdemelo.starwarswiki.domain.entity.model.ItemsImageUrl
import com.vdemelo.starwarswiki.domain.entity.model.getImageUrl
import com.vdemelo.starwarswiki.ui.HomeViewModel
import com.vdemelo.starwarswiki.ui.components.SearchBar
import org.koin.androidx.compose.getViewModel

@Composable
fun ListScreen(
    navController: NavController,
    viewModel: HomeViewModel = getViewModel(),
) {

    val imageUrl = ItemsImageUrl.SPECIES.getImageUrl("1")
    val homeWorld = "Terra"
    val language = "Human Language"
    val classification = "Mammal"

//    DetailsScreen(
//        title = stringResource(id = R.string.details_screen_title_species),
//        imageUrl = imageUrl,
//        firstField = homeWorld,
//        secondField = language,
//        thirdField = classification
//    )

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(id = R.drawable.star_wars_logo),
                contentDescription = stringResource(id = R.string.app_name),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )
            SearchBar( //TODO Tem uma padrao da google tbm, ver se vale a pena
                hint = stringResource(id = R.string.list_screen_search_hint),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            //TODO
            //PokemonList(navController = navController)
        }
    }

}
