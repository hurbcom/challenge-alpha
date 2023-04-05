package com.example.starwars.presentation.model

import com.example.starwars.data.model.MoviesRemote
import com.example.starwars.presentation.ext.beautifyDate
import com.example.starwars.presentation.ext.prepareUrl
import java.io.Serializable

data class Movie(
    val image: String,
    val title: String,
    val launchDate: String,
    val director: String,
    val producer: String,
    val opening: String
) : Serializable, Item(title)

fun List<MoviesRemote>.toMovieList(): List<Movie> {
    val movieList = mutableListOf<Movie>()
    this.forEach {
        movieList.add(
            Movie(
                image = it.image.prepareUrl("films"),
                title = it.title,
                launchDate = it.launchDate.beautifyDate(),
                director = it.director,
                producer = it.producer,
                opening = it.opening.replace("\\r", " ")
                    .replace("\\n", " ")
            )
        )
    }
    return movieList
}