package br.com.mdr.starwars.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val PrimaryColor = Color(0xFFFFE81F)
val SecondaryColor = Color(0xFFDBA90D)
val DarkPrimaryColor = Color(0xFFF30800)
val DarkSecondaryColor = Color(0xFFD40700)

val ShimmerLightGray = Color(0xFFF1F1F1)
val ShimmerMediumGray = Color(0xFFE3E3E3)
val ShimmerDarkGray = Color(0xFF1D1D1D)

// NavigationBarColors
val NavigationIndicatorColor = Color(0x33454545)
val NavigationBarContentColor = Color(0xF2000000)
val SearchBarColor = Color(0x80FFFFFF)
val SelectedItemColor
    @Composable
    get() = if (isSystemInDarkTheme()) DarkPrimaryColor else PrimaryColor
val UnselectedItemColor
    @Composable
    get() = if (isSystemInDarkTheme()) DarkSecondaryColor else SecondaryColor

val ShimmerColor
    @Composable
    get() = if (isSystemInDarkTheme()) ShimmerDarkGray else ShimmerLightGray

val ShimmerMediumColor
    @Composable
    get() = if (isSystemInDarkTheme()) ShimmerDarkGray else ShimmerMediumGray

val ThemeColor
    @Composable
    get() = if (isSystemInDarkTheme()) DarkSecondaryColor else SecondaryColor
