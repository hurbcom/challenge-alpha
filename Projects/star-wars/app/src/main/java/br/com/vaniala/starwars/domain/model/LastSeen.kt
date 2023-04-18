package br.com.vaniala.starwars.domain.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 17/04/23.
 *
 */
@Entity(
    tableName = "last_seen",
)
data class LastSeen(
    @PrimaryKey
    val info: String,
    @Embedded
    var film: Film? = null,
    @Embedded
    var character: People? = null,
    var timestamp: Long = 0L,
    var urlFilm: String? = null,
)
