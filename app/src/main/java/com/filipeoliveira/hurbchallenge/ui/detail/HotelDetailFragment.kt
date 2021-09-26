package com.filipeoliveira.hurbchallenge.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.filipeoliveira.hurbchallenge.databinding.FragmentHotelDetailBinding
import com.filipeoliveira.hurbchallenge.ui.model.HotelUI

class HotelDetailFragment(): Fragment() {

    private lateinit var binding: FragmentHotelDetailBinding
    private lateinit var imageAdapter: RecyclerViewImageAdapter
    private lateinit var hotelUI: HotelUI

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHotelDetailBinding.inflate(inflater)
        imageAdapter = RecyclerViewImageAdapter()
        hotelUI = requireArguments().getSerializable(TAG_HOTEL) as HotelUI

        setupRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setImagesToRecyclerView()
    }

    private fun setImagesToRecyclerView() {
        val urlList = hotelUI.images.map {
            it.url
        }

        imageAdapter.setData(urlList)
    }

    private fun setupRecyclerView() {
        with(binding.fragHotelDetailRc){
            val lm = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            layoutManager = lm
            adapter = imageAdapter
        }
    }

    companion object {
        const val TAG_HOTEL = "TAG_HOTEL"
    }

}