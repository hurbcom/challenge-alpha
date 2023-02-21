package com.example.test.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.core.base.BaseActivity
import com.example.test.R
import com.example.test.databinding.ActivityMainBinding
import com.example.test.presentation.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun hasToolbar(show: Boolean) {
        supportActionBar?.run { if (show) show() else hide() }
    }
}