package com.vdemelo.starwarswiki.ui.screens.planets

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.vdemelo.starwarswiki.R
import com.vdemelo.starwarswiki.ui.screens.common.DetailsScreen

@Composable
fun PlanetDetailsScreen(
    imageUrl: String?,
//    homeWorld: String?,
//    language: String?,
//    classification: String?
) {
    DetailsScreen(
        title = stringResource(id = R.string.details_screen_title_planet),
        imageUrl = imageUrl,
        firstField = null,
        secondField = null,
        thirdField = null
    )
}
