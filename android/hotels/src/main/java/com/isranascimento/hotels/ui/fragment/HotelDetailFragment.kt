package com.isranascimento.hotels.ui.fragment

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.isranascimento.hotels.ui.models.HotelDetailUI

class HotelDetailFragment: Fragment() {


    companion object {
        private const val MODEL = "model"

        fun newInstance(model: HotelDetailUI): HotelDetailFragment =
            HotelDetailFragment().apply {
                this.arguments = bundleOf(MODEL to model)
            }
    }
}