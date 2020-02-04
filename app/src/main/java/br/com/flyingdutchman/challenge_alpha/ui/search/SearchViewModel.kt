package br.com.flyingdutchman.challenge_alpha.ui.search

import androidx.lifecycle.*
import br.com.flyingdutchman.challenge_alpha.data.GroupedResultData
import br.com.flyingdutchman.challenge_alpha.commons.SingleLiveEvent
import br.com.flyingdutchman.challenge_alpha.data.HurbRepository
import br.com.flyingdutchman.challenge_alpha.data.ResultData
import br.com.flyingdutchman.challenge_alpha.ui.search.model.RailsSearchResults
import br.com.flyingdutchman.challenge_alpha.ui.search.model.SearchResult
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

class SearchViewModel(
    private var repository: HurbRepository,
    private var compositeDisposable: CompositeDisposable,
    private var mainScheduler: Scheduler
) : ViewModel(), LifecycleObserver {

    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<SingleLiveEvent<Throwable>>()
    val success = MutableLiveData<SingleLiveEvent<List<SearchResult>>>()
    val successRails = MutableLiveData<SingleLiveEvent<List<RailsSearchResults>>>()


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun loadAllTypesResults() {
        repository.getAllTypeGroupedResults()
            .observeOn(mainScheduler)
            .subscribe(
                {
                    successRails.value = SingleLiveEvent(mapToRailsUiModel(it))
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


    fun loadHotelsSearchResults() {
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
                            mapToSearchResultsUiModel(it)
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

    private fun mapToRailsUiModel(it: List<GroupedResultData>): List<RailsSearchResults> {
        return it.map {
            RailsSearchResults(
                it.name,
                mapToSearchResultsUiModel(it.results)
            )
        }
    }

    private fun mapToSearchResultsUiModel(it: List<ResultData>): List<SearchResult> {
        return it.map { result ->
            SearchResult(
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
                result.city,
                result.amenities,
                result.isHotel
            )
        }
    }

}