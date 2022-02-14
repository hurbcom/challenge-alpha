package com.isranascimento.hotels.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.isranascimento.hotels.R
import com.isranascimento.hotels.databinding.HotelDetailFragmentBinding
import com.isranascimento.hotels.ui.models.HotelDetailUI

class HotelDetailFragment: Fragment() {
    private lateinit var binding: HotelDetailFragmentBinding

    private val uiModel by lazy {
        requireArguments().getParcelable<HotelDetailUI>(MODEL)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return HotelDetailFragmentBinding.inflate(inflater, container, false)
            .also {
                binding = it
            }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            hotelName.text = uiModel.name
            hotelDescription.text = uiModel.description
            hotelRating.rating = uiModel.starCount
            hotelLocation.text = getString(R.string.hotel_list_location_text, uiModel.city, uiModel.state)
        }

    }

    companion object {
        private const val MODEL = "model"

        fun newInstance(model: HotelDetailUI): HotelDetailFragment =
            HotelDetailFragment().apply {
                this.arguments = bundleOf(MODEL to model)
            }
    }
}