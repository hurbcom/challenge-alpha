package com.filipeoliveira.hurbchallenge.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.filipeoliveira.hurbchallenge.databinding.FragmentHotelListBinding

class HotelFavoriteListFragment: Fragment() {

    private lateinit var binding: FragmentHotelListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHotelListBinding.inflate(inflater)

        return binding.root
    }
}