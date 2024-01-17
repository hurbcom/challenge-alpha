package com.wesleyerick.podracer.view.vehicles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wesleyerick.podracer.databinding.FragmentVehiclesBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class VehiclesFragment : Fragment() {

    companion object {
        fun newInstance() = VehiclesFragment()
    }

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
    }
}
