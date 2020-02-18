package viniciusapp.com.br.hurbtest.ui.activities

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import viniciusapp.com.br.hurbtest.models.ResultsModel
import viniciusapp.com.br.hurbtest.repository.RestManager

class MainViewModel: ViewModel() {

    var hotelsLiveData: MutableLiveData<List<ResultsModel>> = MutableLiveData()
    var errorLiveData: MutableLiveData<String> = MutableLiveData()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    private var page: Int = 1

    fun executeRequest(city: String) {
       RestManager.getEndpoints().getHotels(city, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

            .subscribe({
                hotels -> hotelsLiveData.value = hotels.results
            }, {
                errorLiveData.value = "Resultado não encontrado, por favor tente novamente mais tarde"
                Log.d("Error", it.message!!)
            }).apply { compositeDisposable.add(this) }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}