package com.example.starwars.presentation.feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.starwars.databinding.FragmentDetailsMovieBinding
import com.example.starwars.presentation.ext.picassoLoading
import com.example.starwars.presentation.model.Movie
import com.squareup.picasso.Picasso


class DetailsMovieFragment : Fragment() {
    lateinit var binding: FragmentDetailsMovieBinding
    private val navArgs: DetailsMovieFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsMovieBinding.inflate(inflater)
        setupView(navArgs.movie)
        return binding.root
    }

    private fun setupView(movie: Movie) {
        Picasso.get()
            .picassoLoading(movie.image, binding.fdMovieImage, binding.progressBar)
        binding.fdTitle.text = movie.title
        binding.fdLaunch.text = movie.launchDate
        binding.fdDirector.text = movie.director
        binding.fdProduce.text = movie.producer
        binding.fdOpening.text = movie.opening
    }
}