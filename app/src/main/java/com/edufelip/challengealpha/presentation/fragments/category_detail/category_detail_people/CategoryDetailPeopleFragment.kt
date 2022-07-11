package com.edufelip.challengealpha.presentation.fragments.category_detail.category_detail_people

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.edufelip.challengealpha.R
import com.edufelip.challengealpha.databinding.FragmentCategoryDetailBinding
import com.edufelip.challengealpha.presentation.fragments.category_detail.CategoryDetailBaseFragment
import javax.inject.Inject

class CategoryDetailPeopleFragment @Inject constructor(

): CategoryDetailBaseFragment() {

    private val args by navArgs<CategoryDetailPeopleFragmentArgs>()

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
        val character = args.people
        Glide.with(requireContext()).setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.placeholder)
        ).load(character.imageUrl).into(binding.categoryListItemImageView)
        binding.categoryDetailFieldOne.text =
            "${requireActivity().getString(R.string.category_detail_people_name_string)}: ${character.name}"
        binding.categoryDetailFieldTwo.text =
            "${requireActivity().getString(R.string.category_detail_people_gender_string)}: ${character.gender}"
        binding.categoryDetailFieldThree.text =
            "${requireActivity().getString(R.string.category_detail_people_height_string)}: ${character.height}"
        binding.categoryDetailFieldFour.text =
            "${requireActivity().getString(R.string.category_detail_people_skin_string)}: ${character.skinColor}"
        binding.categoryDetailFieldFive.text =
            "${requireActivity().getString(R.string.category_detail_people_birth_string)}: ${character.birthYear}"
    }
}