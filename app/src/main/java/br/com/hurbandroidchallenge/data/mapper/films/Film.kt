package br.com.hurbandroidchallenge.data.mapper.films

import androidx.compose.ui.layout.ContentScale
import br.com.hurbandroidchallenge.commom.extension.idFromUrl
import br.com.hurbandroidchallenge.commom.extension.ifNull
import br.com.hurbandroidchallenge.commom.extension.toDate
import br.com.hurbandroidchallenge.commom.extension.toRoman
import br.com.hurbandroidchallenge.data.local.model.FilmEntity
import br.com.hurbandroidchallenge.data.remote.config.ApiUrls
import br.com.hurbandroidchallenge.data.remote.model.FilmDto
import br.com.hurbandroidchallenge.domain.model.Film
import br.com.hurbandroidchallenge.domain.model.ItemModel

fun FilmEntity.toFilm() = this.run {
    val id = url.idFromUrl()
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

fun FilmDto.toFilm() = this.run {
    val id = url?.idFromUrl()
    Film(
        id = id ifNull  0,
        title = "Episode ${episodeId?.toRoman()}: $title",
        characters = characters.orEmpty(),
        url = url.orEmpty(),
        species = species.orEmpty(),
        starships = starships.orEmpty(),
        vehicles = vehicles.orEmpty(),
        planets = planets.orEmpty(),
        director = director.orEmpty(),
        episodeId = episodeId ifNull 0,
        openingCrawl = openingCrawl.orEmpty(),
        producer = producer.orEmpty(),
        releaseDate = releaseDate.orEmpty(),
        image = "${ApiUrls.imageBaseUrl}films/${id}.jpg"
    )
}

fun FilmDto.toEntity() = this.run {
    val id = url?.idFromUrl()
    FilmEntity(
        id = id ifNull 0,
        title = "Episode ${episodeId?.toRoman()}: $title",
        characters = characters.orEmpty(),
        url = url.orEmpty(),
        species = species.orEmpty(),
        starships = starships.orEmpty(),
        vehicles = vehicles.orEmpty(),
        planets = planets.orEmpty(),
        director = director.orEmpty(),
        episodeId = episodeId ifNull 0,
        openingCrawl = openingCrawl.orEmpty(),
        producer = producer.orEmpty(),
        releaseDate = releaseDate.orEmpty(),
        image = "${ApiUrls.imageBaseUrl}films/${id}.jpg"
    )
}

fun Film.toModel() = ItemModel(
    url = url,
    image = image,
    fields = listOf(
        title to "",
        "Director" to director,
        "Release date" to releaseDate.toDate()
    ),
    contentScale = ContentScale.FillWidth,
    aspectRatio = 4f / 5.5f,
)