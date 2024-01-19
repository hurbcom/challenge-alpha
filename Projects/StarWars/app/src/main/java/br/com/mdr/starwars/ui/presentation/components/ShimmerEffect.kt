package br.com.mdr.starwars.ui.presentation.components

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import br.com.mdr.starwars.ui.theme.Dimens.CATEGORY_ITEM_HEIGHT
import br.com.mdr.starwars.ui.theme.Dimens.CATEGORY_ITEM_SHADOW_HEIGHT
import br.com.mdr.starwars.ui.theme.Dimens.EXTRA_SMALL_PADDING
import br.com.mdr.starwars.ui.theme.Dimens.MEDIUM_PADDING
import br.com.mdr.starwars.ui.theme.Dimens.SMALL_PADDING
import br.com.mdr.starwars.ui.theme.ShimmerColor
import br.com.mdr.starwars.ui.theme.ShimmerMediumColor

@Composable
fun ShimmerEffect() {
    LazyColumn(
        contentPadding = PaddingValues(all = SMALL_PADDING),
        verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
    ) {
        items(count = 5) {
            AnimatedShimmerItem()
        }
    }
}

@Composable
fun AnimatedShimmerItem() {
    val transition = rememberInfiniteTransition(label = "shimmer")
    val alphaAnim by transition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 500,
                easing = FastOutLinearInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "shimmer"
    )

    ShimmerItem(alpha = alphaAnim)
}

@Composable
fun ShimmerItem(alpha: Float) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .alpha(alpha)
            .height(CATEGORY_ITEM_HEIGHT),
        color = ShimmerColor,
        shape = RoundedCornerShape(size = MEDIUM_PADDING)
    ) {
        ConstraintLayout {
            val (surface1, surface2) = createRefs()
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(CATEGORY_ITEM_SHADOW_HEIGHT)
                    .constrainAs(surface1) {
                        bottom.linkTo(parent.bottom)
                    },
                color = ShimmerMediumColor,
                shape = RoundedCornerShape(
                    bottomStart = MEDIUM_PADDING,
                    bottomEnd = MEDIUM_PADDING
                ),
            ){}
            Surface(
                modifier = Modifier
                    .alpha(alpha = alpha)
                    .height(15.dp)
                    .fillMaxWidth(0.3f)
                    .constrainAs(surface2) {
                        bottom.linkTo(surface1.bottom)
                        start.linkTo(surface1.start, margin = MEDIUM_PADDING)
                        top.linkTo(surface1.top)
                    },
                color = Color.LightGray,
                shape = RoundedCornerShape(EXTRA_SMALL_PADDING)
            ){}
        }
    }
}

@Preview
@Composable
fun ShimmerItemPreview() {
    AnimatedShimmerItem()
}