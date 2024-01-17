package com.wesleyerick.podracer.domain.usecase.starships

data class StarshipsUseCases(
    val getList: GetStarshipsListUseCase,
    val getDetails: GetStarshipDetailsUseCase,
)