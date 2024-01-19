package br.com.mdr.starwars.ui.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import br.com.mdr.starwars.R
import br.com.mdr.starwars.ui.presentation.base.BaseDetailViewModel
import br.com.mdr.starwars.ui.theme.ThemeColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreenTopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    navController: NavHostController,
    detailViewModel: br.com.mdr.starwars.ui.presentation.base.BaseDetailViewModel,
    isFavorite: Boolean
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        title = {},
        navigationIcon = {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.favorite_icon),
                    tint = ThemeColor
                )
            }
        },
        actions = {
            IconButton(
                onClick = {
                    detailViewModel.setFavorite()
                }
            ) {
                Icon(
                    painter = painterResource(
                        id = if (isFavorite) {
                            R.drawable.ic_favorite_filled
                        } else {
                            R.drawable.ic_favorite
                        }
                    ),
                    contentDescription = stringResource(id = R.string.favorite_icon),
                    tint = ThemeColor
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}