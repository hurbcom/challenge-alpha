package com.example.hurb_challenge.app.presentation.home

import com.example.hurb_challenge.app.domain.model.Character
import com.example.hurb_challenge.app.domain.usecase.GetCharactersUseCase
import com.example.hurb_challenge.app.presentation.home.HomeBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    useCase: GetCharactersUseCase,
    dispatcher: CoroutineDispatcher
) : HomeBaseViewModel<Character>(useCase, dispatcher) {
    init {
        loadCardItems()
    }
}
