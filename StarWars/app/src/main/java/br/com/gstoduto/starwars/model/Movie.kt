package br.com.gstoduto.starwars.model

import androidx.room.ColumnInfo

data class Movie(
    @ColumnInfo(name = "id") var id: Long  = 0L,
    val title: String,
    @ColumnInfo(name = "episode_id") var episodeId: String?  = null,
    @ColumnInfo(name = "opening_crawl") var openingCrawl: String?  = null,
    val director: String,
    val producer: String,
    val url: String,
    val inMyList: Boolean = false
)

