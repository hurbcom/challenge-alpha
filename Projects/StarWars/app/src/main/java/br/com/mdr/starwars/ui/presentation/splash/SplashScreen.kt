package br.com.mdr.starwars.ui.presentation.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.mdr.starwars.R
import br.com.mdr.starwars.ui.theme.Dimens.MEDIUM_PADDING

@Composable
fun SplashScreen(onAnimationComplete: () -> Unit) {

    val scale = remember { Animatable(0f) }

    if (scale.value == 1f) {
        onAnimationComplete()
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 6000,
                delayMillis = 200
            )
        )
    }
    Splash(scale.value)
}

@Composable
fun Splash(scale: Float) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    transformOrigin = TransformOrigin.Center
                )
                .padding(horizontal = MEDIUM_PADDING),
            painter = painterResource(id = R.drawable.app_logo),
            contentDescription = stringResource(R.string.app_logo)
        )
    }
}

@Composable
@Preview
fun SplashScreenPreview() {
    Splash(1f)
}