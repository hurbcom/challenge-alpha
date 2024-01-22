package com.wesleyerick.podracer.view.vehicles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.wesleyerick.podracer.databinding.FragmentVehiclesBinding
import com.wesleyerick.podracer.util.gone
import com.wesleyerick.podracer.util.listener
import com.wesleyerick.podracer.util.show
import com.wesleyerick.podracer.view.ListAdapter
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
        binding.vehiclesProgressBar.show()

        viewModel.apply {
            getList()
            vehiclesList.listener(viewLifecycleOwner) {
                println("lista veiculos -> $it")
                binding.vehiclesRecycler.apply {
                    adapter = ListAdapter(it) { itemListId ->
                        val action =
                            VehiclesFragmentDirections
                                .actionVehiclesFragmentToVehicleDetailsFragment()
                                .setVehicleId(itemListId)
                        findNavController().navigate(action)
                    }
                    layoutManager = LinearLayoutManager(requireContext())
                }
                binding.vehiclesProgressBar.gone()
            }

            onError.listener(viewLifecycleOwner) {
                if (it.isNotEmpty()) {
                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT)
                        .show()
                }
                binding.vehiclesProgressBar.gone()
            }
        }
    }
}
