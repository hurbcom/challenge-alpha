package br.com.hurbandroidchallenge.data.remote.data_sources

import br.com.hurbandroidchallenge.data.remote.model.FilmDto
import br.com.hurbandroidchallenge.data.remote.model.PeopleDto
import br.com.hurbandroidchallenge.data.remote.model.PlanetDto
import br.com.hurbandroidchallenge.data.remote.model.base.PagedListResponse

interface StarWarsBookRemoteDataSource {

    suspend fun getCharacters(url: String): PagedListResponse<PeopleDto>

    suspend fun getCharacterByUrl(url: String): PeopleDto

    suspend fun getFilms(url: String): PagedListResponse<FilmDto>

    suspend fun getFilmByUrl(url: String): FilmDto

    suspend fun getPlanets(url: String): PagedListResponse<PlanetDto>

    suspend fun getPlanetByUrl(url: String): PlanetDto

}