package com.br.myapplication.data.repository.film

import com.br.myapplication.data.model.Film

interface IFilmRepository {
    suspend fun getFilmList(page: String): List<Film>
}