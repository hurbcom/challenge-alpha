package com.wesleyerick.podracer.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wesleyerick.podracer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        listenersSetup()
        setContentView(binding.root)
    }

    private fun listenersSetup() = with(binding) {
        firstTypeButton.setOnClickListener {
        }
        secondTypeButton.setOnClickListener {
        }
    }
}
