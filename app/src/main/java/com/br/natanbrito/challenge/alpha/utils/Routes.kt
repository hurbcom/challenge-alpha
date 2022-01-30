package com.br.natanbrito.challenge.alpha.utils

import android.view.View
import androidx.navigation.Navigation
import com.br.natanbrito.challenge.alpha.hotel.detail.HotelDetailFragmentDirections
import com.br.natanbrito.challenge.alpha.hotel.list.HotelsFragmentDirections
import com.br.natanbrito.challenge.data.model.results.Result

object Routes {

    fun navigateToDetailsRoute(result: Result, view: View) {
        val action = HotelsFragmentDirections.navigateToHotelDetailFragment(result)
        Navigation.findNavController(view).navigate(action)
    }

    fun navigateToImageRoute(image: String, description: String, view: View) {
        val action = HotelDetailFragmentDirections.navigateToImageFragment(image, description)
        Navigation.findNavController(view).navigate(action)
    }
}
