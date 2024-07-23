package com.example.hurb_challenge.app.presentation.home.films

import com.example.hurb_challenge.app.domain.model.Film
import com.example.hurb_challenge.app.domain.usecase.GetFilmsUseCase
import com.example.hurb_challenge.app.presentation.home.HomeBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class FilmsViewModel @Inject constructor(
    useCase: GetFilmsUseCase,
    dispatcher: CoroutineDispatcher
) : HomeBaseViewModel<Film>(useCase, dispatcher) {

    init {
        loadCardItems()
    }
}
