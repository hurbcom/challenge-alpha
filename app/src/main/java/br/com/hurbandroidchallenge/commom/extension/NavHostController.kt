package br.com.hurbandroidchallenge.commom.extension

import androidx.navigation.NavHostController
import br.com.hurbandroidchallenge.presentation.compose.navigation.Screens

fun NavHostController.backToHome() {
    this.navigate(Screens.Home.route) {
        popUpTo(0)
    }
}