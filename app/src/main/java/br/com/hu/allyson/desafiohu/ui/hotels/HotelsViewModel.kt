package br.com.hu.allyson.desafiohu.ui.hotels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.hu.allyson.desafiohu.domain.Hotels

class HotelsViewModel(application: Application) : AndroidViewModel(application) {

    private var hotels = MutableLiveData<List<Hotels>>()
    private var packages = MutableLiveData<List<Hotels>>()

    fun getHotels():MutableLiveData<List<Hotels>>{
        return hotels
    }

    fun getPackages(): MutableLiveData<List<Hotels>>{
        return packages
    }




}
