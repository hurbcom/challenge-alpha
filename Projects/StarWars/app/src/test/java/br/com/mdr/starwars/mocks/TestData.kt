package br.com.mdr.starwars.mocks

import br.com.mdr.starwars.base.REQUEST_ERROR
import br.com.mdr.starwars.data.remote.model.FilmsResponse
import br.com.mdr.starwars.domain.model.Category
import br.com.mdr.starwars.domain.model.Character
import br.com.mdr.starwars.domain.model.Film
import br.com.mdr.starwars.domain.model.FilmRemoteKeys
import br.com.mdr.starwars.domain.model.LastSeen
import br.com.mdr.starwars.domain.model.PageState
import br.com.mdr.starwars.extension.SerializationExtension.jsonToObject
import br.com.mdr.starwars.extension.getJsonFromAssetsOrResources
import kotlinx.coroutines.flow.flow
import java.net.ConnectException

val categories: PageState<List<Category>> = PageState.Success(getCategories())

val emptyCategories: PageState<List<Category>> = PageState.Empty

val emptyMoviesResponse = FilmsResponse(
    count = 1,
    next = null,
    previous = null,
    results = listOf()
)
val filmsResponse: FilmsResponse =
    getJsonFromAssetsOrResources("films-response.json")
        .jsonToObject<FilmsResponse>() ?: emptyMoviesResponse

val filmsMorePageResponse: FilmsResponse =
    getJsonFromAssetsOrResources("films-more-page-response.json")
        .jsonToObject<FilmsResponse>() ?: emptyMoviesResponse

val emptyLastSeen = listOf<LastSeen>()

val lastSeen: PageState<List<LastSeen>> = PageState.Success(getLastSeen())

val emptyRemoteKeys = listOf<FilmRemoteKeys>()

val mockedException = ConnectException(REQUEST_ERROR)

val mockedError = PageState.Error(mockedException)

private fun getCategories(): List<Category> {
    return getJsonFromAssetsOrResources("categories-list.json")
        .jsonToObject<List<Category>>() ?: emptyList()
}

fun getLastSeen(): List<LastSeen> {
    return listOf(
        LastSeen(
            lastSeenId = "4",
            film = Film(
                id = 4,
                title = "A New Hope",
                openingCrawl = "",
                url = "",
                director = "",
                producer = "",
                dateCreated = ""
            )
        ),
        LastSeen(
            lastSeenId = "Luke Skywalker",
            character = Character(
                name = "Luke Skywalker",
                birthYear = "",
                height = "",
                mass = "",
                gender = "",
                profileUrl = "",
                hairColor = "",
                skinColor = "",
                eyeColor = ""
            )
        )
    )
}

fun emitCategories(value: PageState<List<Category>>) = flow {
    this.emit(value)
}

fun emitLastSeen(value: PageState<List<LastSeen>>) = flow {
    this.emit(value)
}
