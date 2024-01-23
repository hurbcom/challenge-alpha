package com.wesleyerick.podracer.view.vehicles.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.wesleyerick.podracer.databinding.FragmentVehiclesBinding
import com.wesleyerick.podracer.util.gone
import com.wesleyerick.podracer.util.listener
import com.wesleyerick.podracer.util.show
import com.wesleyerick.podracer.view.component.PodracerCircularProgress
import com.wesleyerick.podracer.view.component.PodracerFilter
import com.wesleyerick.podracer.view.component.filterByName
import com.wesleyerick.podracer.view.vehicles.VehiclesViewModel
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

    private fun setupView()= with(binding) {
        setupProgress()
        isShowingProgress(true)

        viewModel.apply {
            getList()
            vehiclesList.listener(viewLifecycleOwner) {

                var adapterList = it

                vehiclesFilter.setContent {
                    PodracerFilter(
                        items = it,
                        filterCriteria = ::filterByName
                    ) { filteredList, searchText ->
                        adapterList = if (searchText.isEmpty()) it else filteredList

                        vehiclesRecycler.apply {
                            val myAdapter = ListAdapter(adapterList) { itemListId ->
                                onItemListClick(itemListId)
                            }
                            myAdapter.notifyDataSetChanged()
                            adapter = myAdapter
                            layoutManager = LinearLayoutManager(requireContext())
                        }
                    }
                }
                isShowingProgress(it.isNotEmpty())
            }

            onError.listener(viewLifecycleOwner) {
                if (it.isNotEmpty()) {
                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT)
                        .show()
                }
                isShowingProgress(it.isNotEmpty())
            }
        }
    }

    private fun onItemListClick(itemListId: String) = findNavController()
        .navigate(
            VehiclesFragmentDirections
                .actionVehiclesFragmentToVehicleDetailsFragment()
                .setVehicleId(itemListId)
        )

    private fun isShowingProgress(isNotEmpty: Boolean) = with(binding) {
        if (isNotEmpty) {
            vehiclesRecycler.show()
            vehiclesProgressBar.gone()
        } else {
            vehiclesRecycler.gone()
            vehiclesProgressBar.show()
        }
    }

    private fun setupProgress() = binding.vehiclesProgressBar.setContent {
        PodracerCircularProgress()
    }
}
