package br.com.mdr.starwars.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.mdr.starwars.common.Constants.FILM_REMOTE_KEYS_TABLE
import br.com.mdr.starwars.data.pagingsource.DEFAULT_PAGE_SIZE

@Entity(tableName = FILM_REMOTE_KEYS_TABLE)
class FilmRemoteKeys(
    @PrimaryKey
    override val id: Int,
    override val prevPage: String?,
    override val nextPage: String?,
    override val lastUpdated: Long?
): BaseRemoteKeys(id, prevPage, nextPage, lastUpdated)

open class BaseRemoteKeys(
    open val id: Int = 0,
    open val prevPage: String?,
    open val nextPage: String?,
    open val lastUpdated: Long?
) {
    fun getPreviousIntPage(): Int = prevPage?.last()?.digitToIntOrNull() ?: DEFAULT_PAGE_SIZE

    fun getNextIntPage(): Int = nextPage?.last()?.digitToIntOrNull() ?: DEFAULT_PAGE_SIZE

}