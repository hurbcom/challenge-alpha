package com.br.natanbrito.challenge.domain.usecases

import com.br.natanbrito.challenge.data.model.Hotel


interface GetHotelsUseCase {
    suspend operator fun invoke(): Hotel?
}