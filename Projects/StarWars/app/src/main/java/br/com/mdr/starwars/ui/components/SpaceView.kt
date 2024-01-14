package br.com.mdr.starwars.ui.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview

data class Star(
    var x: Float,
    var y: Float,
    var alpha: Float,
) {
    private val initialAlpha = alpha

    fun update(value: Float) {
        val x = (value - initialAlpha).toDouble()
        val newAlpha = 0.5f + (0.5f * Math.sin(x).toFloat())
        alpha = newAlpha
    }
}

@Composable
fun SpaceView(
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val infinitelyAnimatedFloat = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 2f * Math.PI.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(5000),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )
    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Black),
    ) {

        val dimens = with(LocalDensity.current) {
            Pair(maxWidth.toPx(), maxHeight.toPx())
        }
        //val height = maxHeight.toPx()
        val stars = remember {
            buildList {
                repeat(500) {
                    val x = (Math.random() * dimens.first).toFloat()
                    val y = (Math.random() * dimens.second).toFloat()
                    val alpha = (Math.random() * 2.0 * Math.PI).toFloat()
                    add(Star(x, y, alpha))
                }
            }
        }
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
        ) {
            for (star in stars) {
                star.update(infinitelyAnimatedFloat.value)
                drawCircle(
                    color = Color.White,
                    center = Offset(star.x, star.y),
                    radius = 2f,
                    alpha = star.alpha,
                )
            }
        }
    }
}

@Preview
@Composable
fun SpacePreview() {
    SpaceView()
}