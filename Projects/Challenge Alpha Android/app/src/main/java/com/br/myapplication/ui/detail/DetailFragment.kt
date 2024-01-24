package com.br.myapplication.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.br.myapplication.databinding.FragmentDetailBinding
import com.squareup.picasso.Picasso


class DetailFragment: Fragment() {


    private var _binding: FragmentDetailBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        setData()

        return binding.root
    }

    private fun setData() {
        val args = arguments
        binding.detailName.text = args?.getString("first_item")
        binding.detail.text = args?.getString("second_item")
        binding.detailThird.text = args?.getString("third_item")
        binding.detailFourth.text = args?.getString("fourth_item")
        binding.detailFifth.text = args?.getString("fifth_item")

        Picasso.get()
            .load(args?.getString("image"))
            .into(binding.detailImage)
    }
}