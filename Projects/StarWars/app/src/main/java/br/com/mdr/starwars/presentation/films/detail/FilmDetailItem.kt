package br.com.mdr.starwars.presentation.films.detail

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import br.com.mdr.starwars.R
import br.com.mdr.starwars.domain.model.Film
import br.com.mdr.starwars.presentation.components.HtmlText
import br.com.mdr.starwars.ui.theme.Dimens
import br.com.mdr.starwars.ui.theme.ThemeColor
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun FilmDetailItem(film: Film) {
    val scrollState = rememberScrollState()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = Dimens.MEDIUM_PADDING)
            .verticalScroll(scrollState)
    ) {
        val (filmImage, titleLabel, releaseLabel, dirLabel,
            prodLabel, descLabel) = createRefs()

        AsyncImage(
            modifier = Modifier
                .fillMaxHeight(fraction = 0.45f)
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(
                        bottomEnd = Dimens.MEDIUM_PADDING,
                        bottomStart = Dimens.MEDIUM_PADDING
                    )
                )
                .constrainAs(filmImage) {
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                },
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(film.filmUrl)
                .placeholder(R.drawable.app_logo)
                .error(R.drawable.ic_error)
                .build(),
            contentDescription = String.format(
                stringResource(id = R.string.film_image),
                film.title
            ),
            contentScale = ContentScale.Crop
        )
        Text(
            modifier = Modifier
                .constrainAs(titleLabel) {
                    end.linkTo(parent.end, margin = Dimens.MEDIUM_PADDING)
                    start.linkTo(parent.start, margin = Dimens.MEDIUM_PADDING)
                    top.linkTo(filmImage.bottom, margin = Dimens.MEDIUM_PADDING)
                    width = Dimension.fillToConstraints
                },
            text = film.episode,
            style = MaterialTheme.typography.titleLarge,
            color = Color.White
        )
        HtmlText(
            modifier = Modifier
                .constrainAs(releaseLabel) {
                    end.linkTo(titleLabel.end)
                    start.linkTo(titleLabel.start)
                    top.linkTo(titleLabel.bottom, margin = Dimens.MEDIUM_PADDING)
                    width = Dimension.fillToConstraints
                },
            html = String.format(
                stringResource(id = R.string.date_created),
                film.dateCreated
            ),
            color = ThemeColor
        )
        HtmlText(
            modifier = Modifier
                .constrainAs(dirLabel) {
                    end.linkTo(titleLabel.end)
                    start.linkTo(titleLabel.start)
                    top.linkTo(releaseLabel.bottom, margin = Dimens.SMALL_PADDING)
                    width = Dimension.fillToConstraints
                },
            html = String.format(
                stringResource(id = R.string.director_label),
                film.director
            ),
            color = ThemeColor
        )
        HtmlText(
            modifier = Modifier
                .constrainAs(prodLabel) {
                    end.linkTo(titleLabel.end)
                    start.linkTo(titleLabel.start)
                    top.linkTo(dirLabel.bottom, margin = Dimens.SMALL_PADDING)
                    width = Dimension.fillToConstraints
                },
            html = String.format(
                stringResource(id = R.string.producer_label),
                film.producer
            ),
            color = ThemeColor
        )
        Text(
            modifier = Modifier
                .constrainAs(descLabel) {
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    top.linkTo(prodLabel.bottom, margin = Dimens.MEDIUM_PADDING)
                    width = Dimension.fillToConstraints
                },
            text = film.openingCrawl,
            style = MaterialTheme.typography.bodyLarge,
            color = ThemeColor,
            textAlign = TextAlign.Center
        )
    }
}