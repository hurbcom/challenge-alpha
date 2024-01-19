package br.com.mdr.starwars.ui.presentation.categories

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.constraintlayout.compose.ConstraintLayout
import br.com.mdr.starwars.R
import br.com.mdr.starwars.domain.model.Category
import br.com.mdr.starwars.ui.theme.Dimens
import br.com.mdr.starwars.utils.upperCaseFirstChar
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size

@Composable
fun CategoryItem(category: Category, onCategoryClick: (Category) -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.CATEGORY_ITEM_HEIGHT),
        shape = RoundedCornerShape(size = Dimens.MEDIUM_PADDING),
        onClick = {
            onCategoryClick(category)
        }
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(category.categoryUrl)
                .placeholder(R.drawable.app_logo)
                .error(R.drawable.ic_error)
                .size(Size.ORIGINAL)
                .build(),
            contentDescription = category.name,
            contentScale = ContentScale.Crop
        )
        ConstraintLayout {
            val (surface, label) = createRefs()
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.CATEGORY_ITEM_SHADOW_HEIGHT)
                    .constrainAs(surface) {
                        bottom.linkTo(parent.bottom)
                    }
                    .alpha(0.2f),
                color = Color.Black,
                shape = RoundedCornerShape(
                    bottomStart = Dimens.MEDIUM_PADDING,
                    bottomEnd = Dimens.MEDIUM_PADDING
                ),
            ){}
            Text(
                text = category.categoryName.upperCaseFirstChar(),
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(label) {
                        bottom.linkTo(surface.bottom)
                        start.linkTo(surface.start, margin = Dimens.MEDIUM_PADDING)
                        top.linkTo(surface.top)
                    },
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}