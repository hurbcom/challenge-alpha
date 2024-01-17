package com.wesleyerick.podracer.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.wesleyerick.podracer.databinding.ActivityMainBinding
import com.wesleyerick.podracer.util.listener
import com.wesleyerick.podracer.view.vehicles.VehiclesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val viewModel by viewModel<VehiclesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        viewModel.getList()
        viewModel.getDetails("4")

        listenersSetup()
        setContentView(binding.root)
    }

    private fun listenersSetup() = with(binding) {
        viewModel.apply {
            list.listener(this@MainActivity) {
                println("lista veiculos -> $it")
            }

            vehicleDetails.listener(this@MainActivity) {
                println("lista vehicleDetails -> $it")
            }

            onError.listener(this@MainActivity) {
                println("lista veiculos onError")

                Toast.makeText(this@MainActivity, "lista veiculos onError", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        firstTypeButton.setOnClickListener {
        }
        secondTypeButton.setOnClickListener {
        }
    }
}
