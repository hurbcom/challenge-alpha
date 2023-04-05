package com.example.starwars.presentation.feature

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.starwars.databinding.FragmentDetailsPeopleBinding
import com.example.starwars.presentation.ext.picassoLoading
import com.example.starwars.presentation.model.People
import com.squareup.picasso.Picasso


class DetailsPeopleFragment : Fragment() {
    private lateinit var binding: FragmentDetailsPeopleBinding
    private val navArgs: DetailsPeopleFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsPeopleBinding.inflate(inflater)
        setupView(navArgs.people)
        return binding.root
    }

    private fun setupView(people: People){
        Picasso.get()
            .picassoLoading(people.image, binding.fdPeopleImage, binding.progressBar)
        binding.fdName.text = people.name
        binding.fdHeight.text = people.height
        binding.fdMass.text = people.mass
        binding.fdEyesColor.text = people.eyesColor
        binding.fdBirthYear.text = people.birthYear
    }
}