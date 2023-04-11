package com.example.challengealphaandroid.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.challengealphaandroid.BuildConfig
import com.example.challengealphaandroid.R
import com.example.challengealphaandroid.model.Person
import com.example.challengealphaandroid.model.Planet
import com.example.challengealphaandroid.model.Starship
import com.example.challengealphaandroid.ui.detail.DetailPersonActivity
import com.example.challengealphaandroid.ui.detail.DetailPlanetActivity
import com.example.challengealphaandroid.ui.detail.DetailStarshipActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            setContent {
                MainScreenView()
            }
        }
    }

    @Composable
    fun PeopleList(peopleList: List<Person>) {
        val gridState = rememberLazyGridState(
        )
        val context = LocalContext.current
        LazyVerticalGrid(
            state = gridState,
            columns = GridCells.Fixed(1),
            content = {
                items(peopleList) { person ->
                    var isFavorite by remember {
                        mutableStateOf(person.isFavorite)
                    }
                    isFavorite = person.isFavorite
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .clickable { val intent = Intent(context, DetailPersonActivity::class.java)
                                intent.putExtra("PERSON", person)
                                startActivity(intent) },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier
                                .size(72.dp)
                                .clip(RectangleShape),
                            painter = rememberImagePainter(
                                BuildConfig.RESOURCES + "characters/" + person.id.toString() + ".jpg",
                                builder = {
                                    error(R.drawable.image_not_found_icon)
                                }),
                            contentDescription = null
                        )
                        Column(
                            Modifier.padding(start = 16.dp)
                        ) {
                            Text(
                                text = getString(R.string.name_label, person.name),
                                style = MaterialTheme.typography.subtitle1
                            )
                            Text(
                                text = getString(R.string.birthyear_label, person.birthYear),
                                style = MaterialTheme.typography.caption
                            )
                            Text(
                                text = getString(
                                    R.string.homeworld_label,
                                    person.homeworld.homeName
                                ),
                                style = MaterialTheme.typography.caption
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Column(horizontalAlignment = Alignment.End) {
                            Image(
                                painter = painterResource(if (isFavorite) R.drawable.ic_star_filled else R.drawable.ic_star_outlined),
                                contentDescription = "Favorite",
                                modifier = Modifier
                                    .size(32.dp)
                                    .clickable {
                                        isFavorite = !isFavorite
                                        viewModel.updatePersonFavorite(person, !person.isFavorite)
                                    }
                            )
                            if (person.recentlySeen)
                                Image(
                                    painter = painterResource(R.drawable.ic_eye),
                                    contentDescription = "Recent",
                                    modifier = Modifier
                                        .size(32.dp)
                                )
                        }
                    }
                }
            }
        )
    }

    @Composable
    fun StarshipList(starshipList: List<Starship>) {
        val gridState = rememberLazyGridState()
        val context = LocalContext.current
        LazyVerticalGrid(
            state = gridState,
            columns = GridCells.Fixed(1),
            content = {
                items(starshipList) { starship ->
                    var isFavorite by remember {
                        mutableStateOf(starship.isFavorite)
                    }
                    isFavorite = starship.isFavorite
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .clickable { val intent = Intent(context, DetailStarshipActivity::class.java)
                                intent.putExtra("STARSHIP", starship)
                                startActivity(intent) },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier
                                .size(72.dp)
                                .clip(RectangleShape),
                            painter = rememberImagePainter(
                                BuildConfig.RESOURCES + "starships/" + starship.id.toString() + ".jpg",
                                builder = {
                                    error(R.drawable.image_not_found_icon)
                                }),
                            contentDescription = null
                        )
                        Column(
                            Modifier
                                .padding(start = 16.dp)
                                .weight(1f)
                        ) {
                            Text(
                                text = getString(R.string.starship_name_label, starship.name),
                                style = MaterialTheme.typography.subtitle1
                            )
                            Text(
                                text = getString(R.string.starship_model_label, starship.model),
                                style = MaterialTheme.typography.caption
                            )
                            Text(
                                text = getString(
                                    R.string.starship_class_label,
                                    starship.starshipClass
                                ),
                                style = MaterialTheme.typography.caption
                            )
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Image(
                                painter = painterResource(if (isFavorite) R.drawable.ic_star_filled else R.drawable.ic_star_outlined),
                                contentDescription = "Favorite",
                                modifier = Modifier
                                    .size(32.dp)
                                    .clickable {
                                        isFavorite = !isFavorite
                                        viewModel.updateStarshipFavorite(
                                            starship,
                                            !starship.isFavorite
                                        )
                                    }

                            )
                            if (starship.recentlySeen)
                                Image(
                                    painter = painterResource(R.drawable.ic_eye),
                                    contentDescription = "Recent",
                                    modifier = Modifier
                                        .size(32.dp)
                                )
                        }
                    }
                }
            }
        )
    }

    @Composable
    fun PlanetList(planetList: List<Planet>) {
        val gridState = rememberLazyGridState()
        val context = LocalContext.current
        LazyVerticalGrid(
            state = gridState,
            columns = GridCells.Fixed(1),
            content = {
                items(planetList) { planet ->
                    var isFavorite by remember {
                        mutableStateOf(planet.isFavorite)
                    }
                    isFavorite = planet.isFavorite
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .clickable { val intent = Intent(context, DetailPlanetActivity::class.java)
                                intent.putExtra("PLANET", planet)
                                startActivity(intent) },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier
                                .size(72.dp)
                                .clip(RectangleShape),
                            painter = rememberImagePainter(
                                BuildConfig.RESOURCES + "planets/" + planet.id.toString() + ".jpg",
                                builder = {
                                    error(R.drawable.image_not_found_icon)
                                }
                            ),
                            contentDescription = null
                        )
                        Column(
                            Modifier
                                .padding(start = 16.dp)
                                .weight(1f)
                        ) {
                            Text(
                                text = getString(R.string.planet_name_label, planet.name),
                                style = MaterialTheme.typography.subtitle1
                            )
                            Text(
                                text = getString(
                                    R.string.population_model_label,
                                    planet.population
                                ),
                                style = MaterialTheme.typography.caption
                            )
                            Text(
                                text = getString(R.string.diameter_label, planet.diameter),
                                style = MaterialTheme.typography.caption
                            )
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Image(
                                painter = painterResource(if (isFavorite) R.drawable.ic_star_filled else R.drawable.ic_star_outlined),
                                contentDescription = "Favorite",
                                modifier = Modifier
                                    .size(32.dp)
                                    .clickable {
                                        isFavorite = !isFavorite
                                        viewModel.updatePlanetFavorite(planet, !planet.isFavorite)
                                    }

                            )
                            if (planet.recentlySeen)
                                Image(
                                    painter = painterResource(R.drawable.ic_eye),
                                    contentDescription = "Recent",
                                    modifier = Modifier
                                        .size(32.dp)
                                )
                        }
                    }
                }
            }
        )
    }

    @Composable
    fun Loading() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

    @Composable
    fun ErrorFetching(message: String) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable { viewModel.loadFromCache() },
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Error: $message")
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = getString(R.string.click_cache))
            }
        }
    }

    @Composable
    inline fun <reified T> ListScreen(
        viewModel: MainViewModel,
        listGetter: (UiStateMain) -> List<T>?
    ) {
        val uiState by viewModel.state.collectAsState()

        when {
            uiState.inProgress -> {
                Loading()
            }
            uiState.error != null -> {
                ErrorFetching(uiState.error ?: "Unknown error")
            }
            else -> {
                listGetter(uiState)?.let { itemList ->
                    when (T::class) {
                        Planet::class -> PlanetList(itemList as List<Planet>)
                        Starship::class -> StarshipList(itemList as List<Starship>)
                        Person::class -> PeopleList(itemList as List<Person>)
                        else -> throw IllegalArgumentException("Unsupported item type: ${T::class}")
                    }
                }
            }
        }
    }

    @Composable
    fun NavigationGraph(navController: NavHostController) {
        NavHost(navController, startDestination = BottomNavItem.People.screen_route) {
            composable(BottomNavItem.People.screen_route) {
                PeopleScreen(viewModel = viewModel)
            }
            composable(BottomNavItem.Starships.screen_route) {
                StarshipScreen(viewModel = viewModel)
            }
            composable(BottomNavItem.Planets.screen_route) {
                PlanetScreen(viewModel = viewModel)
            }
        }
    }

    @Composable
    fun PeopleScreen(viewModel: MainViewModel) {
        ListScreen(viewModel) { it.peopleList }
    }

    @Composable
    fun StarshipScreen(viewModel: MainViewModel) {
        ListScreen(viewModel) { it.starshipList }
    }

    @Composable
    fun PlanetScreen(viewModel: MainViewModel) {
        ListScreen(viewModel) { it.planetList }
    }

    @Composable
    fun BottomNavigation(navController: NavController) {
        val items = listOf(
            BottomNavItem.Planets,
            BottomNavItem.People,
            BottomNavItem.Starships
        )
        BottomNavigation(
            backgroundColor = colorResource(id = R.color.teal_200),
            contentColor = Color.Black,
            modifier = Modifier.height(96.dp)
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            items.forEach { item ->
                BottomNavigationItem(
                    icon = {
                        Image(
                            modifier = Modifier
                                .size(72.dp)
                                .clip(RectangleShape),
                            painter = rememberImagePainter(item.image),
                            contentDescription = null
                        )
                    },
                    label = {
                        Text(
                            text = item.title,
                            fontSize = 14.sp
                        )
                    },
                    selectedContentColor = Color.Black,
                    unselectedContentColor = Color.Black.copy(0.4f),
                    alwaysShowLabel = true,
                    selected = currentRoute == item.screen_route,
                    onClick = {
                        navController.navigate(item.screen_route) {

                            navController.graph.startDestinationRoute?.let { screen_route ->
                                popUpTo(screen_route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }

    @Composable
    fun MainScreenView() {
        val navController = rememberNavController()
        val searchText = remember { mutableStateOf("") }
        val currentDestination = remember { mutableStateOf("") }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Minha Tela Principal") },
                    backgroundColor = Color.Blue,
                    contentColor = Color.White,
                    actions = {
                        TextField(
                            value = searchText.value,
                            onValueChange = { searchText.value = it },
                            placeholder = { Text(text = "Digite aqui para filtrar") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            textStyle = TextStyle(color = Color.White),
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                textColor = Color.White
                            )
                        )
                    }
                )
            },
            bottomBar = { BottomNavigation(navController = navController) },
            content = { padding ->
                Column(
                    modifier = Modifier
                        .padding(padding)

                ) {
                    NavigationGraph(navController = navController)
                }
            })

        LaunchedEffect(searchText.value, currentDestination.value) {
            when (currentDestination.value) {
                "people" -> viewModel.filterPeople(searchText.value)
                "starship" -> viewModel.filterStarship(searchText.value)
                "planet" -> viewModel.filterPlanet(searchText.value)
            }
        }

        val lambda = NavController.OnDestinationChangedListener { controller, destination, arguments ->
            currentDestination.value = destination.route ?: ""
        }

        navController.addOnDestinationChangedListener(lambda)

        DisposableEffect(Unit) {
            onDispose {
                navController.removeOnDestinationChangedListener(lambda)
            }
        }

    }

}

sealed class BottomNavItem(var title: String, var screen_route: String, val image: String) {

    object People : BottomNavItem("People", "people", BuildConfig.PEOPLE)
    object Starships : BottomNavItem("Starships", "starship", BuildConfig.STARTSHIPS)
    object Planets : BottomNavItem("Planets", "planet", BuildConfig.PLANETS)
}

