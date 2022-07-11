package com.edufelip.challengealpha.presentation.fragments.category_detail.category_detail_vehicle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.edufelip.challengealpha.R
import com.edufelip.challengealpha.presentation.fragments.category_detail.CategoryDetailBaseFragment
import javax.inject.Inject

class CategoryDetailVehicleFragment @Inject constructor(

) : CategoryDetailBaseFragment() {

    private val args by navArgs<CategoryDetailVehicleFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupArgs()
    }

    private fun setupArgs() {
        val vehicle = args.vehicle
        Glide.with(requireContext()).setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.placeholder)
        ).load(vehicle.imageUrl).into(binding.categoryListItemImageView)
        binding.categoryDetailFieldOne.text =
            "${requireActivity().getString(R.string.category_detail_vehicles_name_string)}: ${vehicle.name}"
        binding.categoryDetailFieldTwo.text =
            "${requireActivity().getString(R.string.category_detail_vehicles_model_string)}: ${vehicle.model}"
        binding.categoryDetailFieldThree.text =
            "${requireActivity().getString(R.string.category_detail_vehicles_cargo_capacity_string)}: ${vehicle.passengers}"
        binding.categoryDetailFieldFour.text =
            "${requireActivity().getString(R.string.category_detail_vehicles_consumables_string)}: ${vehicle.consumables}"
        binding.categoryDetailFieldFive.text =
            "${requireActivity().getString(R.string.category_detail_vehicles_hyperdriving_rate_string)}: ${vehicle.cargoCapacity}"
    }
}