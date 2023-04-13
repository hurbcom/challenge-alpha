package br.com.hurbandroidchallenge.presentation.compose.navigation

import androidx.navigation.NavHostController
import br.com.hurbandroidchallenge.commom.extension.putArgument

sealed class ScreenNavOperations(val route: String, val argumentKey: String) {

    fun <T> routeWithArgument(argument: T?): String {
        return route.replace(oldValue = "{$argumentKey}", newValue = argument.toString())
    }

    fun navigateUp(navHostController: NavHostController) {
        navHostController.navigate(route = route) {
            popUpTo(0)
        }
    }

    fun <T> navigateWithParcelableArgument(
        navHostController: NavHostController,
        argumentValue: T?
    ) {
        navHostController.currentBackStackEntry?.savedStateHandle?.putArgument(
            key = argumentKey,
            value = argumentValue
        )
        navHostController.navigate(route)
    }

}