package br.com.hurbandroidchallenge.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.com.hurbandroidchallenge.presentation.compose.theme.HurbAndroidChallengeTheme
import br.com.hurbandroidchallenge.presentation.navGraph.NavHostScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HurbAndroidChallengeTheme {
                NavHostScreen()
            }
        }
    }
}