package br.com.rvvaranda.hu.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.rvvaranda.hu.Model.Hotel
import br.com.rvvaranda.hu.Repository.HotelsRepository

class HotelsViewModel : ViewModel() {
    val lstHotels = MutableLiveData<MutableList<Hotel>>()
    var totalPages = 0

    fun loadAllHotels(page: Int) {

        HotelsRepository().getHotels(page) { success, payload ->
            if (success) {
                lstHotels.value = payload?.hotels
                totalPages = payload?.pagination?.let { payload.pagination.count } ?: 0
            }
        }
    }
}