package br.com.mdr.starwars.ui.presentation.characters.detail

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
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import br.com.mdr.starwars.R
import br.com.mdr.starwars.domain.model.Character
import br.com.mdr.starwars.ui.presentation.components.HtmlText
import br.com.mdr.starwars.ui.theme.Dimens.MEDIUM_PADDING
import br.com.mdr.starwars.ui.theme.ThemeColor
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun CharacterDetailContent(character: Character) {
    val scrollState = rememberScrollState()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = MEDIUM_PADDING)
            .verticalScroll(scrollState)
    ) {
        val (
            image, titleLabel, birthdayLabel, heightLabel,
            massLabel, genderLabel, hairLabel, skinLabel, eyeLabel
        ) = createRefs()

        AsyncImage(
            modifier = Modifier
                .fillMaxHeight(fraction = 0.45f)
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(
                        bottomEnd = MEDIUM_PADDING,
                        bottomStart = MEDIUM_PADDING
                    )
                )
                .constrainAs(image) {
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                },
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(character.characterUrl)
                .placeholder(R.drawable.app_logo)
                .error(R.drawable.ic_error)
                .build(),
            contentDescription = String.format(
                stringResource(id = R.string.film_image),
                character.name
            ),
            contentScale = ContentScale.Crop
        )
        Text(
            modifier = Modifier
                .constrainAs(titleLabel) {
                    end.linkTo(parent.end, margin = MEDIUM_PADDING)
                    start.linkTo(parent.start, margin = MEDIUM_PADDING)
                    top.linkTo(image.bottom, margin = MEDIUM_PADDING)
                    width = Dimension.fillToConstraints
                },
            text = character.name,
            style = MaterialTheme.typography.titleLarge,
            color = Color.White
        )
        HtmlText(
            modifier = Modifier
                .constrainAs(birthdayLabel) {
                    end.linkTo(titleLabel.end)
                    start.linkTo(titleLabel.start)
                    top.linkTo(titleLabel.bottom, margin = MEDIUM_PADDING)
                    width = Dimension.fillToConstraints
                },
            html = String.format(
                stringResource(id = R.string.birth_year),
                character.birthYear
            ),
            color = ThemeColor
        )
        HtmlText(
            modifier = Modifier
                .constrainAs(heightLabel) {
                    end.linkTo(titleLabel.end)
                    start.linkTo(titleLabel.start)
                    top.linkTo(birthdayLabel.bottom, margin = MEDIUM_PADDING)
                    width = Dimension.fillToConstraints
                },
            html = String.format(
                stringResource(id = R.string.height),
                character.height
            ),
            color = ThemeColor
        )
        HtmlText(
            modifier = Modifier
                .constrainAs(massLabel) {
                    end.linkTo(titleLabel.end)
                    start.linkTo(titleLabel.start)
                    top.linkTo(heightLabel.bottom, margin = MEDIUM_PADDING)
                    width = Dimension.fillToConstraints
                },
            html = String.format(
                stringResource(id = R.string.mass),
                character.mass
            ),
            color = ThemeColor
        )
        HtmlText(
            modifier = Modifier
                .constrainAs(genderLabel) {
                    end.linkTo(titleLabel.end)
                    start.linkTo(titleLabel.start)
                    top.linkTo(massLabel.bottom, margin = MEDIUM_PADDING)
                    width = Dimension.fillToConstraints
                },
            html = String.format(
                stringResource(id = R.string.gender),
                character.gender
            ),
            color = ThemeColor
        )
        HtmlText(
            modifier = Modifier
                .constrainAs(hairLabel) {
                    end.linkTo(titleLabel.end)
                    start.linkTo(titleLabel.start)
                    top.linkTo(genderLabel.bottom, margin = MEDIUM_PADDING)
                    width = Dimension.fillToConstraints
                },
            html = String.format(
                stringResource(id = R.string.hair_color),
                character.hairColor
            ),
            color = ThemeColor
        )
        HtmlText(
            modifier = Modifier
                .constrainAs(skinLabel) {
                    end.linkTo(titleLabel.end)
                    start.linkTo(titleLabel.start)
                    top.linkTo(hairLabel.bottom, margin = MEDIUM_PADDING)
                    width = Dimension.fillToConstraints
                },
            html = String.format(
                stringResource(id = R.string.skin_color),
                character.skinColor
            ),
            color = ThemeColor
        )
        HtmlText(
            modifier = Modifier
                .constrainAs(eyeLabel) {
                    end.linkTo(titleLabel.end)
                    start.linkTo(titleLabel.start)
                    top.linkTo(skinLabel.bottom, margin = MEDIUM_PADDING)
                    width = Dimension.fillToConstraints
                },
            html = String.format(
                stringResource(id = R.string.eye_color),
                character.eyeColor
            ),
            color = ThemeColor
        )
    }
}
