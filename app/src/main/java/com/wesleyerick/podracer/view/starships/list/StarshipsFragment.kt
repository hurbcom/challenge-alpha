package com.wesleyerick.podracer.view.starships.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.wesleyerick.podracer.R
import com.wesleyerick.podracer.data.model.starships.Starship
import com.wesleyerick.podracer.util.TypeEnum
import com.wesleyerick.podracer.util.getItemListId
import com.wesleyerick.podracer.util.getPhotoUrl
import com.wesleyerick.podracer.view.component.PodracerCircularProgress
import com.wesleyerick.podracer.view.component.PodracerFilter
import com.wesleyerick.podracer.view.component.filterByName
import com.wesleyerick.podracer.view.starships.StarshipsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class StarshipsFragment : Fragment() {

    private val viewModel by viewModel<StarshipsViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.getList()
        return ComposeView(requireContext()).apply {
            setContent {
                StarshipsFragmentScreen()
            }
        }
    }

    @Composable
    fun StarshipsFragmentScreen() {
        val list by viewModel.list.observeAsState()
        val onError by viewModel.onError.observeAsState()

        var isShowingProgress by remember { mutableStateOf(true) }
        var newList by remember { mutableStateOf<List<Starship>>(emptyList()) }


        MaterialTheme {
            val backgroundImage = painterResource(id = R.drawable.home_background)
            Image(
                painter = backgroundImage,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "StarShips List",
                    style = TextStyle(
                        fontSize = 32.sp,
                        fontFamily = FontFamily(Font(R.font.starjedi)),
                        color = colorResource(id = R.color.yellow_title_text)
                    ),
                    modifier = Modifier.padding(top = 32.dp)
                )
                PodracerCircularProgress(isShowingProgress)
                list?.let {
                    if (it.isEmpty()) {
                        isShowingProgress = true
                    } else {

                        PodracerFilter(items = it, ::filterByName) { filteredList, _ ->
                            newList = filteredList
                        }

                        LazyColumn(
                            modifier = Modifier.padding(bottom = 32.dp)
                        ) {
                            items(newList.size) { position ->
                                StarshipItem(newList, position)
                            }
                        }
                        isShowingProgress = false
                    }
                }
                onError?.let {
                    if (it.isNotEmpty()) {
                        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                        isShowingProgress = false
                    }
                }


                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(
                            enabled = true,
                            state = ScrollState(0)
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                }
            }
        }
    }

    @OptIn(ExperimentalCoilApi::class)
    @Composable
    private fun StarshipItem(starshipList: List<Starship>, position: Int) {
        val starshipItem = starshipList[position]
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color(0x92000000))
                .padding(top = 8.dp, bottom = 8.dp, start = 24.dp, end = 24.dp)
                .clickable {
                    val action =
                        StarshipsFragmentDirections
                            .actionStarshipsFragmentToStarshipDetailFragment()
                            .setStarshipId(
                                getItemListId(
                                    url = starshipList[position].url
                                )
                            )
                    findNavController().navigate(action)
                }
        ) {
            Row {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(8.dp))
                ) {

                    var isDefaultImageEnabled by remember {
                        mutableStateOf(false)
                    }

                    val painter = rememberImagePainter(
                        getPhotoUrl(starshipItem.url, path = TypeEnum.STARSHIPS.path),
                        builder = {
                            this.listener(
                                onSuccess = { _, _ ->
                                    isDefaultImageEnabled = false
                                },
                                onError = { _, exception ->
                                    isDefaultImageEnabled = true
                                }
                            )
                        }
                    )

                    Image(
                        painter = if (!isDefaultImageEnabled) {
                            painter
                        } else {
                            painterResource(id = R.drawable.placeholder)
                        },
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Column {
                    Text(
                        text = starshipItem.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp, end = 8.dp, top = 8.dp),
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.starjedi)),
                            color = colorResource(id = R.color.yellow_title_text)
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = "Consumables: ${starshipItem.consumables}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp, end = 8.dp, top = 8.dp),
                        color = Color.White,
                        fontFamily = FontFamily.SansSerif,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = "Hyperdrive Rating: ${starshipItem.hyperdrive_rating}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp, end = 8.dp, top = 8.dp),
                        color = Color.White,
                        fontFamily = FontFamily.SansSerif,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(16.dp)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(Color(0x2CFFFFFF))
                    .padding(start = 16.dp, end = 16.dp)
            )
        }
    }

    @Preview(showSystemUi = true, showBackground = true)
    @Composable
    fun StarshipsFragmentScreenPreview() {
        StarshipsFragmentScreen()
    }
}