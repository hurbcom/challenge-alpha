package com.example.hurb_challenge.app.presentation.filmdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hurb_challenge.app.data.ConnectionFailure
import com.example.hurb_challenge.app.data.Failure
import com.example.hurb_challenge.app.data.Fetching
import com.example.hurb_challenge.app.data.HttpFailure
import com.example.hurb_challenge.app.data.Success
import com.example.hurb_challenge.app.domain.model.CardItem
import com.example.hurb_challenge.app.domain.model.Character
import com.example.hurb_challenge.app.domain.toCardItem
import com.example.hurb_challenge.app.domain.usecase.GetCharactersInFilmUseCase
import com.example.hurb_challenge.app.presentation.common.Empty
import com.example.hurb_challenge.app.presentation.common.Error
import com.example.hurb_challenge.app.presentation.common.Loaded
import com.example.hurb_challenge.app.presentation.common.Loading
import com.example.hurb_challenge.app.presentation.common.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmDetailsViewModel @Inject constructor(
    private val useCase: GetCharactersInFilmUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _charactersStateFlow: MutableStateFlow<State<List<CardItem<Character>>>> = MutableStateFlow(
        Empty
    )
    val charactersStateFlow: StateFlow<State<List<CardItem<Character>>>> = _charactersStateFlow.asStateFlow()

    fun loadCharactersByUrl(urls: List<String>) {
        viewModelScope.launch(dispatcher) {
            useCase.call(urls)
                .collect { result ->
                    when (result) {
                        is Fetching -> {
                            _charactersStateFlow.emit(Loading)
                        }
                        is Failure -> {
                            var message = ""
                            message = when (result) {
                                is HttpFailure -> "Http error: ${result.errorCode}"
                                is ConnectionFailure -> "Connectivity Error"
                                else -> "Unknown error"
                            }
                            _charactersStateFlow.emit(Error(message))
                        }
                        is Success -> {
                            val items = result.value.map { character ->
                                character.toCardItem()
                            }
                            _charactersStateFlow.emit(Loaded(items))
                        }
                    }
                }
        }
    }
}
