package br.com.loubake.challenge_hu.hotels

interface HotelsContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showHotelsList()
        fun showErrorLayout()
    }

    interface Presenter {
        fun loadHotels()
        fun setHotelsList()
    }

}