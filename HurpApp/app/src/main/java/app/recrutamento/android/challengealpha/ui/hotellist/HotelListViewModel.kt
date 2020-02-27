package app.recrutamento.android.challengealpha.ui.hotellist

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.recrutamento.android.challengealpha.R
import app.recrutamento.android.challengealpha.model.hotel.Hotel
import app.recrutamento.android.challengealpha.repository.HotelRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

class HotelListViewModel(
    private val repository: HotelRepository,
    private val application: Application
) : ViewModel() {

    var hotel = MutableLiveData<MutableList<Hotel>>()
    val loading = ObservableBoolean(false)
    val message = ObservableField<String>()
    private var myJob: Job? = null

    fun load(local: String, pageNumber: String, numberStars: String){
        loading.set(true)
        message.set("")

        myJob = viewModelScope.launch {
            repository.listAllHotels(local, pageNumber, { items ->

                items.forEach { Timber.d(it.toString()) }

                if (items.isEmpty()) {
                    message.set(application.getString(R.string.empty))
                }

                hotel.postValue(searchAndSort(items, numberStars))

                loading.set(false)
                message.set("")

            }, {
                message.set(application.getString(R.string.failed))
                loading.set(false)
            })
        }
    }

    fun searchAndSort(items: MutableList<Hotel>, numberStars: String): MutableList<Hotel> {
        var hotels = ArrayList<Hotel>()

        if (numberStars.isEmpty())
            hotels.addAll(items)

        if (numberStars.isNotEmpty())
            hotels.addAll(items.filter { it.stars == numberStars.toInt() })

        hotels.sortByDescending { it.stars }

        return hotels.toMutableList()
    }

}

