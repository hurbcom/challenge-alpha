package br.com.mdr.starwars.common

object Constants {
    const val IMAGE_EXTENSION = ".jpg"
    const val FILM_ID_KEY = "filmId"
    const val CHARACTER_NAME_KEY = "characterName"

    const val BASE_URL = "https://swapi.dev/"

    const val IMAGE_BASE_URL = "https://starwars-visualguide.com/assets/img/"

    const val CATEGORIES_URL = "categories/"
    const val FILMS_URL = "films/"
    const val CHARACTERS_URL = "characters/"

    const val FILM_DATABASE_TABLE = "film_table"
    const val FILM_REMOTE_KEYS_TABLE = "film_remote_keys_table"
    const val CHARACTER_TABLE = "character_table"
    const val CHARACTER_REMOTE_KEYS_TABLE = "character_remote_keys_table"
    const val LAST_SEEN_TABLE = "last_seen_table"
    const val DEFAULT_PAGE_SIZE = 10
    const val DEFAULT_PAGE_SPACE_SIZE = 4
    const val DEFAULT_DB_CACHE_TIME = 720 //In minutes
}