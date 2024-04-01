package br.com.gstoduto.starwars.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.gstoduto.starwars.model.Specie

@Entity(tableName = "species")
class SpecieEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") var id: Long  = 0L,
    val name: String,
    val classification: String,
    val designation: String,
    @ColumnInfo(name = "average_height") var averageHeight: String?  = null,
    @ColumnInfo(name = "skin_colors") var skinColors: String?  = null,
    @ColumnInfo(name = "hair_colors") var hairColors: String?  = null,
    @ColumnInfo(name = "eye_colors") var eyeColors: String?  = null,
    @ColumnInfo(name = "average_lifespan") var averageLifespan: String?  = null,
    val language: String,
    val url: String,
    val inMyList: Boolean = false
)

fun SpecieEntity.toSpecie() = Specie(
    id = id,
    name = name,
    classification = classification,
    designation = designation,
    averageHeight = averageHeight,
    skinColors = skinColors,
    hairColors = hairColors,
    eyeColors = eyeColors,
    averageLifespan = averageLifespan,
    language = language,
    url = url,
    inMyList = inMyList
)
