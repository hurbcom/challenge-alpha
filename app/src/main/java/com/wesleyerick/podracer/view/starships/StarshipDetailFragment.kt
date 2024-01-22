package com.wesleyerick.podracer.view.starships

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                    .fillMaxSize()
                    .padding(8.dp)
            ) {

                starshipDetails?.let {

                    if (it.url.isEmpty()) {
                        PodracerCircularProgress()
                    } else {
                        StarshipContent(it)
                    }
                }
                // Image

                BackButton()
            }
        }

    }

    @OptIn(ExperimentalCoilApi::class)
    private @Composable
    fun StarshipContent(it: Starship) {
        Image(
            painter = rememberImagePainter(
                getPhotoUrl(it.url, path = ImageTypeEnum.STARSHIPS.path)
            ),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        )

        // Texts and Button
        Column(
            modifier = Modifier
                .fillMaxSize()
//                .weight(1f)
                .padding(8.dp)
        ) {
            // Title
            Text(
                text = "Title",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                fontSize = 28.sp,
                color = Color.Yellow,
                textAlign = TextAlign.Center
            )

            // Subtitles
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x72000000))
                    .padding(8.dp)
            ) {
                SubtitleText(it.name)
                SubtitleText(it.manufacturer)
                SubtitleText(it.cost_in_credits)
                SubtitleText(it.starship_class)

                // Spacer
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }

    @Composable
    private fun BackButton() {
        Button(
            onClick = {
                      findNavController().popBackStack()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .background(Color.Yellow)
        ) {
            Text(text = "Voltar", color = Color.Black, fontFamily = FontFamily.SansSerif)
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