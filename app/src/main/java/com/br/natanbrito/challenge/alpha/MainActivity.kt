package com.br.natanbrito.challenge.alpha

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.br.natanbrito.challenge.alpha.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
