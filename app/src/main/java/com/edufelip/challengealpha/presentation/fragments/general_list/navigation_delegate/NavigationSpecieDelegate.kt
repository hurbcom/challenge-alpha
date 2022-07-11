package com.edufelip.challengealpha.presentation.fragments.general_list.navigation_delegate

import androidx.navigation.NavController
import com.edufelip.challengealpha.presentation.fragments.general_list.GeneralListMenuFragmentDirections

class NavigationSpecieDelegate : NavigationDelegate {
    override fun navigate(navController: NavController) {
        navController.navigate(
            GeneralListMenuFragmentDirections.actionGeneralListMenuFragmentToCategoryListSpecieFragment()
        )
    }
}