package br.com.flyingdutchman.challenge_alpha.ui

import androidx.lifecycle.*
import br.com.flyingdutchman.challenge_alpha.commons.SingleLiveEvent
import br.com.flyingdutchman.challenge_alpha.data.HurbRepository
import br.com.flyingdutchman.challenge_alpha.data.ResultData
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

class SearchViewModel(
    private var repository: HurbRepository,
    private var compositeDisposable: CompositeDisposable,
    private var mainScheduler: Scheduler
) : ViewModel(), LifecycleObserver {

    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<SingleLiveEvent<Throwable>>()
    val success = MutableLiveData<SingleLiveEvent<List<Result>>>()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun loadResults() {
        repository
            .getHotels()
            .observeOn(mainScheduler)
            .doOnSubscribe {
                loading.value = true
            }
            .subscribe(
                {
                    success.value =
                        SingleLiveEvent(
                            mapToUiModel(it)
                        )
                    loading.value = false
                },
                {
                    error.value =
                        SingleLiveEvent(
                            it
                        )
                    loading.value = false
                }
            )
            .apply {
                compositeDisposable.add(this)
            }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    private fun mapToUiModel(it: List<ResultData>): List<Result> {
        return it.map { result ->
            Result(
                result.id,
                result.name,
                result.url,
                result.description,
                result.shortDescription,
                result.gallery,
                result.freeCancelation,
                result.currentPrice,
                result.oldPrice,
                result.rating,
                result.city
            )
        }
    }
}