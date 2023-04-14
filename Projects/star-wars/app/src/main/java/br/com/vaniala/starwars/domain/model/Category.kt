package br.com.vaniala.starwars.domain.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import br.com.vaniala.starwars.BuildConfig

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 10/04/23.
 *
 */
private const val CHARACTER = "character"
private const val PEOPLE = "people"
private const val CATEGORIES_URL = "categories/"
private const val JPG = ".jpg"

@Entity(
    tableName = "category",
)
data class Category(
    @PrimaryKey
    val name: String,
    val url: String? = null,
) {
    @Ignore
    var image: String? = getUrlImage(name)

    @Ignore
    var nameFormatted: String? = firstCharUpperCase(name)

    private fun firstCharUpperCase(name: String): String {
        if (name.isEmpty()) return ""
        return name.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase() else it.toString()
        }
    }

    private fun getUrlImage(typeCategory: String?): String? {
        if (typeCategory.isNullOrEmpty()) return null
        var type = typeCategory
        if (type == PEOPLE) {
            type = CHARACTER
        }
        return "${BuildConfig.BASE_URL_IMAGES}$CATEGORIES_URL$type$JPG"
    }
}
