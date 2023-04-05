package com.example.hurbandroidchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.hurbandroidchallenge.ui.theme.HurbAndroidChallengeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HurbAndroidChallengeTheme {

            }
        }
    }
}