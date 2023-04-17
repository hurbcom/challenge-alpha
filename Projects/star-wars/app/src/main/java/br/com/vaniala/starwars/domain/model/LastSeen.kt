package br.com.vaniala.starwars.domain.model

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
    val img: String,
    val type: String,
    val timestamp: Long,
)
