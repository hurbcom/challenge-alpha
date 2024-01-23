package com.wesleyerick.podracer.view.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wesleyerick.podracer.R
import com.wesleyerick.podracer.data.model.starships.Starship
import com.wesleyerick.podracer.data.model.vehicles.Vehicle

fun <T> List<T>.filterList(
    query: String,
    filterCriteria: (T, String) -> Boolean,
) = this.filter { item -> filterCriteria(item, query) }

fun filterByName(vehicle: Vehicle, query: String) = vehicle.name.contains(query, ignoreCase = true)
fun filterByName(starship: Starship, query: String) =
    starship.name.contains(query, ignoreCase = true)

@Composable
fun <T> PodracerFilter(
    items: List<T>,
    filterCriteria: (T, String) -> Boolean,
    filteredListCallback: (List<T>, String) -> Unit
) {
    var searchText by remember { mutableStateOf("") }
    var filteredList by remember { mutableStateOf(items) }

    Column {
        TextField(
            value = searchText,
            onValueChange = {
                searchText = it
                filteredList = items.filterList(it) { item, query ->
                    filterCriteria(item, query)
                }
            },
            label = { Text("Search") },
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = null,
                    tint = colorResource(id = R.color.yellow_title_text),
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Black,
                textColor = Color.White,
                cursorColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedLabelColor = colorResource(id = R.color.yellow_title_text),
                unfocusedLabelColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp)
        )
        filteredListCallback.invoke(filteredList, searchText)
    }
}

@Preview
@Composable
fun PodracerFilterPreview() {
    PodracerFilter(
        items = listOf(
            Vehicle(name = "Gol"),
            Vehicle(name = "Vectra"),
            Vehicle(name = "Opala"),
        ),
        ::filterByName
    ) { _, _ -> }
}