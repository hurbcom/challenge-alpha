package br.com.gstoduto.starwars.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.gstoduto.starwars.R
import br.com.gstoduto.starwars.navigation.DrawerItems
import br.com.gstoduto.starwars.navigation.ItemsMenuLateral
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuLateral(
    navController: NavHostController,
    drawerState: DrawerState,
    currentRoute: String?,
    conteudo: @Composable () -> Unit
) {
    val scope = rememberCoroutineScope()

    val drawerItems = listOf(
        DrawerItems(
            ItemsMenuLateral.ItemMovies.icon,
            ItemsMenuLateral.ItemMovies.title,
            ItemsMenuLateral.ItemMovies.route,
        ),
        DrawerItems(
            ItemsMenuLateral.ItemVehicles.icon,
            ItemsMenuLateral.ItemVehicles.title,
            ItemsMenuLateral.ItemVehicles.route,
        ),
        DrawerItems(
            ItemsMenuLateral.ItemSpecies.icon,
            ItemsMenuLateral.ItemSpecies.title,
            ItemsMenuLateral.ItemSpecies.route,
        )
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .background(Color(0xFFB16D08)), contentAlignment = Alignment.Center
                    ) {
                        Column(
                            Modifier.wrapContentSize(),
                            verticalArrangement = Arrangement.SpaceAround,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.star_wars),
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(CircleShape)
                                    .align(Alignment.CenterHorizontally),
                                contentScale = ContentScale.Crop,
                                contentDescription = "Logo do aplicativo",
                            )
                            Text(
                                text = stringResource(id = R.string.app_name),
                                Modifier
                                    .fillMaxWidth()
                                    .padding(top = 16.dp),
                                fontSize = 22.sp,
                                color = Color.White,
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                    drawerItems.forEach { item ->
                        NavigationDrawerItem(
                            icon = {
                                Icon(item.icon, null)
                            },
                            label = { Text(text = item.text) },
                            modifier = Modifier.padding(horizontal = 16.dp),
                            selected = currentRoute == item.route,
                            onClick = {
                                scope.launch { drawerState.close() }
                                navController.navigate(item.route)

                                scope.launch {
                                    drawerState.close()
                                }
                            },
                        )
                    }
                }
            }
        }
    ) {
        conteudo()
    }
}