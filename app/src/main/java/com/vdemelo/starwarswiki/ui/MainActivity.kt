package com.vdemelo.starwarswiki.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vdemelo.starwarswiki.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewBinding()
        setTest()
        setButtonClickListener()
        viewModel.fetchSpecies()
    }

    private fun setViewBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setTest() {
        viewModel.species.observe(this) {
            it?.run {
                binding.title.text = it.name
            }
        }
    }

    private fun setButtonClickListener() {
        binding.button.setOnClickListener {
            viewModel.fetchSpecies()
        }
    }

}
