package com.wesleyerick.podracer.view.vehicles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.wesleyerick.podracer.databinding.FragmentVehiclesBinding
import com.wesleyerick.podracer.util.listener
import org.koin.androidx.viewmodel.ext.android.viewModel

class VehiclesFragment : Fragment() {

    private val viewModel by viewModel<VehiclesViewModel>()

    private lateinit var binding: FragmentVehiclesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentVehiclesBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }

    private fun setupView() {
        viewModel.getList()

        viewModel.apply {
            list.listener(viewLifecycleOwner) {
                println("lista veiculos -> $it")
            }

            onError.listener(viewLifecycleOwner) {
                if (it.isNotEmpty()) {
                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}
