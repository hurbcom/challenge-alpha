package br.com.hurbandroidchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import br.com.hurbandroidchallenge.presentation.compose.theme.HurbAndroidChallengeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HurbAndroidChallengeTheme {
                Surface {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Text(modifier = Modifier.align(Alignment.Center), text = "Hello world")
                    }
                }
            }
        }
    }
}