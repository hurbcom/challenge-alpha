package br.com.gstoduto.starwars.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.gstoduto.starwars.model.Movie

@Entity(tableName = "movies")
class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id", defaultValue = "0") var id: Long,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "episode_id") var episodeId: String? = null,
    @ColumnInfo(name = "opening_crawl") var openingCrawl: String? = null,
    @ColumnInfo(name = "director") var director: String,
    @ColumnInfo(name = "producer") var producer: String,
    @ColumnInfo(name = "url") var url: String,
    @ColumnInfo(name = "inMyList") var inMyList: Boolean = false,
)

fun MovieEntity.toMovie() = Movie(
    id = id,
    title = title,
    episodeId = episodeId,
    openingCrawl = openingCrawl,
    director = director,
    producer = producer,
    url = url,
    inMyList = inMyList
)
