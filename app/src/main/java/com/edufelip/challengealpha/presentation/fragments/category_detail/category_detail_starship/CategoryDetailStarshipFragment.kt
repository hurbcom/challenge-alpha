package com.edufelip.challengealpha.presentation.fragments.category_detail.category_detail_starship

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

class CategoryDetailStarshipFragment @Inject constructor(

) : CategoryDetailBaseFragment() {

    private val args by navArgs<CategoryDetailStarshipFragmentArgs>()

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
        val starship = args.starship
        Glide.with(requireContext()).setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.placeholder)
        ).load(starship.imageUrl).into(binding.categoryListItemImageView)
        binding.categoryDetailFieldOne.text =
            "${requireActivity().getString(R.string.category_detail_starship_name_string)}: ${starship.name}"
        binding.categoryDetailFieldTwo.text =
            "${requireActivity().getString(R.string.category_detail_starship_model_string)}: ${starship.model}"
        binding.categoryDetailFieldThree.text =
            "${requireActivity().getString(R.string.category_detail_starship_cargo_capacity_string)}: ${starship.cargoCapacity}"
        binding.categoryDetailFieldFour.text =
            "${requireActivity().getString(R.string.category_detail_starship_cost_credits_string)}: ${starship.costInCredits}"
        binding.categoryDetailFieldFive.text =
            "${requireActivity().getString(R.string.category_detail_starship_hyperdriving_rate_string)}: ${starship.hyperdriveRating}"
    }
}