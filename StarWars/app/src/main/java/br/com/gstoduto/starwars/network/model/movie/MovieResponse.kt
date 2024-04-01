package br.com.gstoduto.starwars.network.model.movie

import br.com.gstoduto.starwars.database.entities.MovieEntity
import br.com.gstoduto.starwars.ui.components.getIndex

data class MovieResponse(
    val title: String,
    val url: String,
    val episode_id: String,
    val opening_crawl: String,
    val director: String,
    val producer: String,
    val inMyList: Boolean
)

fun MovieResponse.toMovieEntity(): MovieEntity {
    return MovieEntity(
        id = getIndex(url, "/films/").toLong(),
        title = title,
        url = url,
        episodeId = episode_id,
        openingCrawl = opening_crawl,
        director = director,
        producer = producer,
        inMyList = inMyList
    )
}