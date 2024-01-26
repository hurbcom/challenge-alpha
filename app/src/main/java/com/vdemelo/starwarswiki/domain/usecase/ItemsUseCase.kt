package com.vdemelo.starwarswiki.domain.usecase

import com.vdemelo.starwarswiki.domain.entity.model.ItemsImageUrl
import com.vdemelo.starwarswiki.domain.entity.model.getImageUrl

private const val SUFFIX = "/"

class ItemsUseCase {

    // URL examples:
    // species.url = https://swapi.dev/api/species/1/
    // species image = https://starwars-visualguide.com/assets/img/species/1.jpg

    fun getItemId(itemUrl: String): Int? {
        val charsToDrop = if (itemUrl.endsWith(SUFFIX)) 1 else 0
        val id = try {
            itemUrl.dropLast(charsToDrop).takeLastWhile { it.isDigit() }.toInt()
        } catch (e: Exception) {
            null
        }
        return id
    }

    fun getSpeciesImageUrl(id: Int): String {
        return ItemsImageUrl.SPECIES.getImageUrl(id.toString())
    }

    fun getPlanetImageUrl(id: Int): String {
        return ItemsImageUrl.PLANET.getImageUrl(id.toString())
    }


}
