package com.wesleyerick.podracer.view.starships

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.wesleyerick.podracer.R
import com.wesleyerick.podracer.data.model.starships.Starship
import com.wesleyerick.podracer.util.ImageTypeEnum
import com.wesleyerick.podracer.util.getPhotoUrl
import com.wesleyerick.podracer.view.component.PodracerCircularProgress
import org.koin.androidx.viewmodel.ext.android.viewModel

class StarshipDetailFragment : Fragment() {

    private val viewModel by viewModel<StarshipsViewModel>()
    private val arguments by navArgs<StarshipDetailFragmentArgs>()
    private val starshipId by lazy { arguments.starshipId }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.getDetails(starshipId)
        return ComposeView(requireContext()).apply {
            setContent {
                VehicleDetailsScreen()
            }
        }
    }

    @Composable
    private fun VehicleDetailsScreen() {

        val starshipDetails by viewModel.starshipsDetails.observeAsState()
        val onError by viewModel.onError.observeAsState()

        var isShowingProgress by remember { mutableStateOf(true) }

        MaterialTheme {
            ConstraintLayout(Modifier.fillMaxSize()) {

                val (backgroundReference, buttonReference) = createRefs()

                val backgroundImage = painterResource(id = R.drawable.home_background)

                Box(
                    Modifier
                        .fillMaxSize()
                        .constrainAs(backgroundReference) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        }
                ) {
                    Image(
                        painter = backgroundImage,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize(),
                    )
                }

                PodracerCircularProgress(isShowingProgress)

                starshipDetails?.let {

                    if (it.url.isEmpty()) {
                        isShowingProgress = true
                    } else {
                        StarshipContent(it)
                        isShowingProgress = false
                    }
                }
                onError?.let {
                    if (it.isNotEmpty()) {
                        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                        isShowingProgress = false
                    }
                }
                // Image
                BackButton(
                    Modifier
                        .fillMaxWidth()
                        .padding(bottom = 56.dp, start = 24.dp, end = 24.dp)
                        .background(Color.Yellow)
                        .constrainAs(buttonReference) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        }
                )
            }

        }
    }

    @OptIn(ExperimentalCoilApi::class)
    @Composable
    private fun StarshipContent(it: Starship) {
        ConstraintLayout(
            Modifier
                .fillMaxSize()
                .background(Color(0x72000000))

        ) {

            val topGuideline = createGuidelineFromTop(0.3f)

            val (imageReference, contentReference) = createRefs()

            var isDefaultImageEnabled by remember {
                mutableStateOf(false)
            }

            val painter = rememberImagePainter(
                getPhotoUrl(it.url, path = ImageTypeEnum.STARSHIPS.path),
                builder = {
                    this.listener(
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
                modifier = Modifier
                    .aspectRatio(1f)
                    .constrainAs(imageReference) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(topGuideline)
                    }
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .constrainAs(contentReference) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(topGuideline)
                    },
            ) {
                Text(
                    text = it.name,
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontFamily = FontFamily(Font(R.font.starjedi)),
                        color = colorResource(id = R.color.yellow_title_text)
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    SubtitleText("Manufacturer: ${it.manufacturer}")
                    SubtitleText("Passengers: ${it.passengers}")
                    SubtitleText("Starship Class: ${it.starship_class}")
                    SubtitleText("Max Speed: ${it.max_atmosphering_speed}Km/h")

                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
    }

    @Composable
    private fun BackButton(modifier: Modifier) {
        Button(
            onClick = {
                findNavController().popBackStack()
            },
            shape = RoundedCornerShape(16),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.yellow_title_text),
            ),
            modifier = modifier
        ) {
            Text(
                text = "VOLTAR",
                color = colorResource(id = R.color.white_default_text),
                fontFamily = FontFamily.SansSerif
            )
        }
    }

    @Composable
    fun SubtitleText(text: String) {
        Text(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
            fontSize = 20.sp,
            color = Color.White,
            fontFamily = FontFamily.SansSerif
        )
    }
}