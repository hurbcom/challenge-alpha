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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.wesleyerick.podracer.R
import com.wesleyerick.podracer.data.model.starships.Starship
import com.wesleyerick.podracer.util.TypeEnum
import com.wesleyerick.podracer.util.getItemListId
import com.wesleyerick.podracer.util.getPhotoUrl
import com.wesleyerick.podracer.util.values.Dimensions
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
                    text = getString(R.string.starships_list_title),
                    style = TextStyle(
                        fontSize = Dimensions.Text.title,
                        fontFamily = FontFamily(Font(R.font.starjedi)),
                        color = colorResource(id = R.color.yellow_title_text)
                    ),
                    modifier = Modifier.padding(top = Dimensions.Value.dp32)
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
                            modifier = Modifier.padding(bottom = Dimensions.Value.dp32)
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
                .background(colorResource(id = R.color.item_content_background))
                .padding(horizontal = Dimensions.Value.dp24, vertical = Dimensions.Value.dp8)
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
                        .size(Dimensions.Size.imageItemList)
                        .clip(RoundedCornerShape(Dimensions.Shape.roundedCornerShape))
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
                                onError = { _, _ ->
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
                            .padding(horizontal = Dimensions.Value.dp8)
                            .padding(top = Dimensions.Value.dp8),
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.starjedi)),
                            color = colorResource(id = R.color.yellow_title_text)
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    SubtitleListText(
                        beforeValue = getString(R.string.consumables_subtitle),
                        value = starshipItem.consumables
                    )
                    SubtitleListText(
                        beforeValue = getString(R.string.hyperdrive_rating_subtitle),
                        value = starshipItem.hyperdriveRating
                    )

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(Dimensions.Value.dp16)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimensions.Value.dp2)
                    .background(colorResource(id = R.color.item_line_background))
                    .padding(horizontal = Dimensions.Value.dp16)
            )
        }
    }

    @Composable
    private fun SubtitleListText(beforeValue: String, value: String) {
        Text(
            text = "$beforeValue $value",
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimensions.Value.dp8)
                .padding(top = Dimensions.Value.dp8),
            color = colorResource(id = R.color.white_default_text),
            fontFamily = FontFamily.SansSerif,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }

    @Preview(showSystemUi = true, showBackground = true)
    @Composable
    fun StarshipsFragmentScreenPreview() {
        StarshipsFragmentScreen()
    }
}