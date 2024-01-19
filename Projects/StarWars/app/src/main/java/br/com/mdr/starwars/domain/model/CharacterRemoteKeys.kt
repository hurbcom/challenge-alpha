package br.com.mdr.starwars.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.mdr.starwars.common.Constants.CHARACTER_REMOTE_KEYS_TABLE

@Entity(tableName = CHARACTER_REMOTE_KEYS_TABLE)
data class CharacterRemoteKeys(
    @PrimaryKey
    val name: String,
    override val id: Int = 0,
    override val prevPage: String?,
    override val nextPage: String?,
    override val lastUpdated: Long?
): BaseRemoteKeys(id, prevPage, nextPage, lastUpdated)