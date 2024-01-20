package br.com.mdr.starwars.domain.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.mdr.starwars.common.Constants.LAST_SEEN_TABLE

@Entity(tableName = LAST_SEEN_TABLE)
data class LastSeen(
    @PrimaryKey
    val lastSeenId: String,
    @Embedded
    val film: Film? = null,
    @Embedded
    val character: Character? = null
)
