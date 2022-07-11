package com.edufelip.challengealpha.presentation.fragments.category_detail.category_detail_film

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

class CategoryDetailFilmFragment @Inject constructor(

) : CategoryDetailBaseFragment() {

    private val args by navArgs<CategoryDetailFilmFragmentArgs>()

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
        val film = args.film
        Glide.with(requireContext()).setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.placeholder)
        ).load(film.imageUrl).into(binding.categoryListItemImageView)
        binding.categoryDetailFieldOne.text =
            "${requireActivity().getString(R.string.category_detail_film_title_string)}: ${film.title}"
        binding.categoryDetailFieldTwo.text =
            "${requireActivity().getString(R.string.category_detail_film_episode_string)}: ${film.episodeId}"
        binding.categoryDetailFieldThree.text =
            "${requireActivity().getString(R.string.category_detail_film_director_string)}: ${film.director}"
        binding.categoryDetailFieldFour.text =
            "${requireActivity().getString(R.string.category_detail_film_producer_string)}: ${film.producer}"
        binding.categoryDetailFieldFive.text =
            "${requireActivity().getString(R.string.category_detail_film_opening_string)}: ${film.openingCrawl}"
    }
}