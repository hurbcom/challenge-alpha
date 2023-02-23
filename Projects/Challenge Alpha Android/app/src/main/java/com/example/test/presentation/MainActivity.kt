package com.example.test.presentation

import android.os.Bundle
import com.example.core.base.presentation.BaseActivity
import com.example.test.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun hasToolbar(show: Boolean) {
        supportActionBar?.run { if (show) show() else hide() }
    }
}