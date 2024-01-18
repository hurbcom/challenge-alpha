package br.com.mdr.starwars.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.mdr.starwars.common.Constants.FILM_REMOTE_KEYS_DATABASE_TABLE
import br.com.mdr.starwars.data.pagingsource.DEFAULT_PAGE_SIZE

@Entity(tableName = FILM_REMOTE_KEYS_DATABASE_TABLE)
data class FilmRemoteKeys(
    @PrimaryKey()
    val id: Int = 0,
    val prevPage: String?,
    val nextPage: String?,
    val lastUpdated: Long?
) {
    fun getPreviousIntPage(): Int = prevPage?.last()?.digitToIntOrNull() ?: DEFAULT_PAGE_SIZE

    fun getNextIntPage(): Int = nextPage?.last()?.digitToIntOrNull() ?: DEFAULT_PAGE_SIZE

}