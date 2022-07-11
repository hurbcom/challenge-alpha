package com.edufelip.challengealpha.presentation.fragments.category_detail.category_detail_specie

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

class CategoryDetailSpecieFragment @Inject constructor(

) : CategoryDetailBaseFragment() {

    private val args by navArgs<CategoryDetailSpecieFragmentArgs>()

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
        val specie = args.specie
        Glide.with(requireContext()).setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.placeholder)
        ).load(specie.imageUrl).into(binding.categoryListItemImageView)
        binding.categoryDetailFieldOne.text =
            "${requireActivity().getString(R.string.category_detail_specie_name_string)}: ${specie.name}"
        binding.categoryDetailFieldTwo.text =
            "${requireActivity().getString(R.string.category_detail_specie_height_string)}: ${specie.averageHeight}"
        binding.categoryDetailFieldThree.text =
            "${requireActivity().getString(R.string.category_detail_specie_lifespan_string)}: ${specie.averageLifespan}"
        binding.categoryDetailFieldFour.text =
            "${requireActivity().getString(R.string.category_detail_specie_eye_colors_string)}: ${specie.eyeColors}"
        binding.categoryDetailFieldFive.text =
            "${requireActivity().getString(R.string.category_detail_specie_language_string)}: ${specie.language}"
    }
}