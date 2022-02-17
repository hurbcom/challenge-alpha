package com.isranascimento.challengealpha.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

data class TabFragmentModel(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val fragment: Fragment
)
