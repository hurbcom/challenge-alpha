package br.com.hu.allyson.desafiohu.ui.hotels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.hu.allyson.desafiohu.domain.Hotels

class HotelsViewModel : ViewModel() {
    private var hotels = MutableLiveData<List<Hotels>>()

    fun getHotels():MutableLiveData<List<Hotels>>{
        return hotels
    }




}
