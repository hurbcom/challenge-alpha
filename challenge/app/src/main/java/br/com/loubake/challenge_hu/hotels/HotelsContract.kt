package br.com.loubake.challenge_hu.hotels

import br.com.loubake.challenge_hu.data.HotelResults

interface HotelsContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showHotelsList()
        fun showErrorLayout()
        fun setHotelsList(results: List<HotelResults.Hotel>?)
    }

    interface Presenter {
        fun loadHotels()
    }
}