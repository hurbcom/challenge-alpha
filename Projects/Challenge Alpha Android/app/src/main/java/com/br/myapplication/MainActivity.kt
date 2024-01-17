package com.br.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.koin.core.context.startKoin

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startKoin {
            modules()
        }
    }
}