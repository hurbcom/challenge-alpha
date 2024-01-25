package com.wesleyerick.podracer.view.starships.details

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.wesleyerick.podracer.R
import com.wesleyerick.podracer.data.model.starships.Starship
import com.wesleyerick.podracer.util.BLANK
import com.wesleyerick.podracer.util.TypeEnum
import com.wesleyerick.podracer.util.getPhotoUrl
import com.wesleyerick.podracer.util.values.Dimensions
import com.wesleyerick.podracer.view.component.PodracerCircularProgress
import com.wesleyerick.podracer.view.starships.StarshipsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class StarshipDetailsFragment : Fragment() {

    companion object {
        const val GUIDELINE_PERCENTAGE = 0.3F
        const val IMAGE_ASPECT_RATIO = 1F
    }

    private val viewModel by viewModel<StarshipsViewModel>()
    private val arguments by navArgs<StarshipDetailsFragmentArgs>()
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
                        modifier = Modifier.fillMaxSize(),
                    )
                }

                PodracerCircularProgress(isShowingProgress)

                starshipDetails?.let {
                    isShowingProgress = if (it.url.isEmpty()) {
                        true
                    } else {
                        StarshipContent(it)
                        false
                    }
                }
                onError?.let {
                    if (it.isNotEmpty()) {
                        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                        isShowingProgress = false
                    }
                }

                BackButton(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = Dimensions.Padding.backButtonPaddingHorizontal)
                        .padding(bottom = Dimensions.Padding.backButtonPaddingBottom)
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
                .background(colorResource(id = R.color.item_details_content_background))

        ) {
            val topGuideline = createGuidelineFromTop(GUIDELINE_PERCENTAGE)
            val (imageReference, contentReference) = createRefs()
            var isDefaultImageEnabled by remember {
                mutableStateOf(false)
            }
            val painter = rememberImagePainter(
                getPhotoUrl(it.url, path = TypeEnum.STARSHIPS.path),
                builder = {
                    this.listener(
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
                contentDescription = it.name,
                modifier = Modifier
                    .aspectRatio(IMAGE_ASPECT_RATIO)
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
                    .padding(Dimensions.Value.dp8)
                    .constrainAs(contentReference) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(topGuideline)
                    },
            ) {
                Text(
                    text = it.name,
                    style = TextStyle(
                        fontSize = Dimensions.Text.title,
                        fontFamily = FontFamily(Font(R.font.starjedi)),
                        color = colorResource(id = R.color.yellow_title_text)
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Dimensions.Padding.titlePaddingAll)
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(Dimensions.Value.dp8)
                ) {
                    SubtitleText(
                        beforeValue = getString(R.string.manufacturer_subtitle),
                        value = it.manufacturer
                    )
                    SubtitleText(
                        beforeValue = getString(R.string.passengers_subtitle),
                        value = it.passengers
                    )
                    SubtitleText(
                        beforeValue = getString(R.string.class_subtitle),
                        value = it.starshipClass
                    )
                    SubtitleText(
                        beforeValue = getString(R.string.max_speed_subtitle),
                        value = it.maxAtmospheringSpeed,
                        typeValue = if (it.maxAtmospheringSpeed.isDigitsOnly()) {
                            getString(R.string.kilometers_per_hour)
                        } else {
                            BLANK
                        }
                    )

                    Spacer(modifier = Modifier.height(Dimensions.Value.dp32))
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
            shape = RoundedCornerShape(Dimensions.Shape.roundedCornerShape),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.button_background),
            ),
            modifier = modifier
        ) {
            Text(
                text = getString(R.string.back_caps),
                color = colorResource(id = R.color.white_default_text),
                fontFamily = FontFamily.SansSerif
            )
        }
    }

    @Composable
    fun SubtitleText(
        beforeValue: String,
        value: String,
        typeValue: String = BLANK,
    ) {
        Text(
            text = "$beforeValue $value $typeValue",
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = Dimensions.Padding.itemDetailsPaddingHorizontal,
                    vertical = Dimensions.Padding.itemDetailsPaddingVertical
                ),
            fontSize = Dimensions.Text.itemDetailsSubTitle,
            color = colorResource(id = R.color.white_default_text),
            fontFamily = FontFamily.SansSerif
        )
    }
}