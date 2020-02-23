package app.recrutamento.android.challengealpha.ui.hotellist

import android.app.Application
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.recrutamento.android.challengealpha.R
import app.recrutamento.android.challengealpha.model.hotel.Hotel
import app.recrutamento.android.challengealpha.repository.HotelRepository
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class HotelListViewModel(
    private val repository: HotelRepository,
    private val application: Application
) : ViewModel() {

    var hotel = MutableLiveData<MutableList<Hotel>>()
    val loading = ObservableBoolean(false)
    val message = ObservableField<String>()
    private var myJob: Job? = null

    fun load(local: String, pageNumber: String){
        loading.set(true)
        message.set("")

        myJob = viewModelScope.launch {
            repository.listAllHotels(local, pageNumber, { items ->
                hotel.postValue(items.toMutableList())

                if (items.isEmpty()) {
                    message.set(application.getString(R.string.empty))
                }
                loading.set(false)
                items.sortByDescending { it.stars }
                items.addAll(items)
            }, {
                message.set(application.getString(R.string.failed))
                loading.set(false)
            })
        }
    }

}

