package br.com.mdr.starwars.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val PrimaryColor = Color(0xFFFFE81F)
val SecondaryColor = Color(0xFFDBA90D)
val DarkPrimaryColor = Color(0xFFF30800)
val DarkSecondaryColor = Color(0xFF9D0500)


//NavigationBarColors
val NavigationIndicatorColor = Color(0x33454545)
val NavigationBarContentColor = Color(0xF2000000)
val SelectedItemColor
    @Composable
    get() = if (isSystemInDarkTheme()) DarkSecondaryColor else PrimaryColor
val UnselectedItemColor
    @Composable
    get() = if (isSystemInDarkTheme()) DarkSecondaryColor else SecondaryColor