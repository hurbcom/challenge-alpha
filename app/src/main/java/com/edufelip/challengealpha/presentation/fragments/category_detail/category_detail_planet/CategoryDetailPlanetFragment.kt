package com.edufelip.challengealpha.presentation.fragments.category_detail.category_detail_planet

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

class CategoryDetailPlanetFragment @Inject constructor(

) : CategoryDetailBaseFragment() {

    private val args by navArgs<CategoryDetailPlanetFragmentArgs>()

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
        val planet = args.planet
        Glide.with(requireContext()).setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.placeholder)
        ).load(planet.imageUrl).into(binding.categoryListItemImageView)
        binding.categoryDetailFieldOne.text =
            "${requireActivity().getString(R.string.category_detail_planet_name_string)}: ${planet.name}"
        binding.categoryDetailFieldTwo.text =
            "${requireActivity().getString(R.string.category_detail_planet_climate_string)}: ${planet.climate}"
        binding.categoryDetailFieldThree.text =
            "${requireActivity().getString(R.string.category_detail_planet_gravity_string)}: ${planet.gravity}"
        binding.categoryDetailFieldFour.text =
            "${requireActivity().getString(R.string.category_detail_planet_orbital_period_string)}: ${planet.orbitalPeriod}"
        binding.categoryDetailFieldFive.text =
            "${requireActivity().getString(R.string.category_detail_planet_population_string)}: ${planet.population}"
    }
}