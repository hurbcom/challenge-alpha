package com.vdemelo.starwarswiki.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vdemelo.starwarswiki.R
import com.vdemelo.starwarswiki.databinding.ActivityMainBinding
import com.vdemelo.starwarswiki.ui.details.DetailsActivity
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
            val species = viewModel.species.value
            val intent = DetailsActivity.getIntent(
                context = this,
                title = "Species",
                imageUrl = species?.url,
                fieldOne = species?.homeWorld?.run {
                    getString(R.string.details_screen_field_one_species, this)
                },
                fieldTwo = species?.language?.run {
                    getString(R.string.details_screen_field_two_species, this)
                },
                fieldThree = species?.classification?.run {
                    getString(R.string.details_screen_field_three_species, this)
                },
            )
            startActivity(intent)
        }
    }

}
