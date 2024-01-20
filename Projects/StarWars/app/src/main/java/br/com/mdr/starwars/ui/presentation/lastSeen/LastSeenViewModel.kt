package br.com.mdr.starwars.ui.presentation.lastSeen

import br.com.mdr.starwars.domain.model.Character
import br.com.mdr.starwars.domain.model.Film
import br.com.mdr.starwars.domain.model.LastSeen
import br.com.mdr.starwars.domain.model.PageState
import br.com.mdr.starwars.domain.usecase.LastSeenUseCase
import br.com.mdr.starwars.ui.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.single

class LastSeenViewModel(
    private val useCase: LastSeenUseCase
): BaseViewModel() {

    private val _lastSeen = MutableStateFlow<PageState<List<LastSeen>>>(PageState.Loading)
    val lastSeen: StateFlow<PageState<List<LastSeen>>>
        get() = _lastSeen
    val films: List<Film>
        get() = filterFilms()

    val characters: List<Character>
        get() = filterCharacters()

    init {
        getLastSeen()
    }

    override suspend fun refresh() = Unit

    private fun getLastSeen() {
        launch(Dispatchers.IO) {
            _lastSeen.emit(useCase.getLastSeen().single())
        }
    }

    private fun filterFilms(): List<Film> {
        val list = getPageStateResult()

        return mutableListOf<Film>().apply {
            list.forEach {
                it.film?.let { film ->
                    this.add(film)
                }
            }
        }
    }

    private fun filterCharacters(): List<Character> {
        val list = getPageStateResult()

        return mutableListOf<Character>().apply {
            list.forEach {
                it.character?.let { character ->
                    this.add(character)
                }
            }
        }
    }

    private fun getPageStateResult(): List<LastSeen> {
        return if (_lastSeen.value is PageState.Success) {
            try {
                (_lastSeen.value as PageState.Success<List<LastSeen>>).result
            } catch (e: Exception)  {
                listOf()
            }
        } else {
            listOf()
        }
    }
}