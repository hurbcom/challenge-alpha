package com.vdemelo.starwarswiki.ui

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.vdemelo.starwarswiki.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTest()
        setButtonClickListener()
        viewModel.fetchSpecies()
    }

    private fun setTest() {
        val textView = findViewById<TextView>(R.id.title)
        viewModel.species.observe(this) {
            it?.run {
                textView.text = it.name
            }
        }
    }

    private fun setButtonClickListener() {
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            viewModel.fetchSpecies()
        }
    }

}
