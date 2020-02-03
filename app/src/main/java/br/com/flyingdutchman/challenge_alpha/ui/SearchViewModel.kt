package br.com.flyingdutchman.challenge_alpha.ui

import androidx.lifecycle.*
import br.com.flyingdutchman.challenge_alpha.commons.SingleLiveEvent
import br.com.flyingdutchman.challenge_alpha.data.HurbRepository
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
                    loading.value = false
                    success.value =
                        SingleLiveEvent(
                            it.map { result ->
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
                            })
                },
                {
                    loading.value = false
                    error.value =
                        SingleLiveEvent(
                            it
                        )
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
}