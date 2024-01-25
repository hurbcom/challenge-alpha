package com.wesleyerick.podracer.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.wesleyerick.podracer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStatusBarStyle()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setStatusBarStyle() = window.apply {
        WindowCompat.setDecorFitsSystemWindows(this, false)
        this.statusBarColor = android.graphics.Color.TRANSPARENT
    }
}
