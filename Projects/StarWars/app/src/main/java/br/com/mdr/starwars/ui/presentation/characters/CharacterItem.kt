package br.com.mdr.starwars.ui.presentation.characters

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import br.com.mdr.starwars.R
import br.com.mdr.starwars.domain.model.Character
import br.com.mdr.starwars.ui.theme.Dimens
import br.com.mdr.starwars.ui.theme.Dimens.FILM_ITEM_HEIGHT
import br.com.mdr.starwars.ui.theme.Dimens.SMALL_PADDING
import br.com.mdr.starwars.utils.upperCaseFirstChar
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size

@Composable
fun CharacterItem(character: Character, onClick: ((String) -> Unit)) {
    Column {
        Surface(
            modifier = Modifier
                .height(FILM_ITEM_HEIGHT),
            shape = RoundedCornerShape(size = Dimens.MEDIUM_PADDING),
            onClick = {
                onClick(character.name)
            }
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize(),
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data(character.characterUrl)
                    .placeholder(R.drawable.app_logo)
                    .error(R.drawable.ic_error)
                    .size(Size.ORIGINAL)
                    .build(),
                contentDescription = character.name,
                contentScale = ContentScale.Crop
            )
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(SMALL_PADDING),
            text = character.name.upperCaseFirstChar(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
