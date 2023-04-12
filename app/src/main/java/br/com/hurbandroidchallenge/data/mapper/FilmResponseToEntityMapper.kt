package br.com.hurbandroidchallenge.data.mapper

import br.com.hurbandroidchallenge.commom.mapper.Mapper
import br.com.hurbandroidchallenge.data.remote.model.FilmResponse
import br.com.hurbandroidchallenge.domain.model.Film

class FilmResponseToEntityMapper : Mapper<FilmResponse, Film> {
    override fun map(input: FilmResponse) = input.run {
        val id = url?.split("/")?.last { it.isNotBlank() }?.toInt()
        Film(
            id = id ?: 0,
            title = title.orEmpty(),
            characters = characters.orEmpty(),
            url = url.orEmpty(),
            species = species.orEmpty(),
            starships = starships.orEmpty(),
            vehicles = vehicles.orEmpty(),
            planets = planets.orEmpty(),
            director = director.orEmpty(),
            episodeId = episodeId  ?: 0,
            openingCrawl = openingCrawl.orEmpty(),
            producer = producer.orEmpty(),
            releaseDate = releaseDate.orEmpty()
        )
    }
}