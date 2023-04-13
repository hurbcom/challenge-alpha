package br.com.hurbandroidchallenge.data.mapper

import br.com.hurbandroidchallenge.commom.extension.toDate
import br.com.hurbandroidchallenge.commom.extension.toRoman
import br.com.hurbandroidchallenge.data.local.model.FilmEntity
import br.com.hurbandroidchallenge.data.remote.config.ApiUrls
import br.com.hurbandroidchallenge.data.remote.model.FilmDto
import br.com.hurbandroidchallenge.domain.model.Film
import br.com.hurbandroidchallenge.domain.model.ItemModel

fun FilmEntity.toFilm() = this.run {
    val id = url.split("/").last { it.isNotBlank() }.toInt()
    Film(
        id = id,
        title = title,
        characters = characters,
        url = url,
        species = species,
        starships = starships,
        vehicles = vehicles,
        planets = planets,
        director = director,
        episodeId = episodeId,
        openingCrawl = openingCrawl,
        producer = producer,
        releaseDate = releaseDate,
        image = image
    )
}

fun FilmDto.toEntity() = this.run {
    val id = url?.split("/")?.last { it.isNotBlank() }?.toInt()
    FilmEntity(
        id = id ?: 0,
        title = "Episode ${episodeId?.toRoman()}: $title",
        characters = characters.orEmpty(),
        url = url.orEmpty(),
        species = species.orEmpty(),
        starships = starships.orEmpty(),
        vehicles = vehicles.orEmpty(),
        planets = planets.orEmpty(),
        director = director.orEmpty(),
        episodeId = episodeId ?: 0,
        openingCrawl = openingCrawl.orEmpty(),
        producer = producer.orEmpty(),
        releaseDate = releaseDate.orEmpty(),
        image = "${ApiUrls.imageBaseUrl}films/${id}.jpg"
    )
}

fun Film.toModel() = ItemModel(
    image = image,
    fields = listOf(
        title to "",
        "Diretor" to director,
        "Data de lançamento" to releaseDate.toDate()
    )
)