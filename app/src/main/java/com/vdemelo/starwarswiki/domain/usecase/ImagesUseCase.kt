package com.vdemelo.starwarswiki.domain.usecase

import com.vdemelo.starwarswiki.domain.entity.model.ItemsImageUrl
import com.vdemelo.starwarswiki.domain.entity.model.getImageUrl

class ImagesUseCase {

    // URL examples:
    // species.url = https://swapi.dev/api/species/1/
    // species image = https://starwars-visualguide.com/assets/img/species/1.jpg

    fun getItemNumber(itemUrl: String): Int? {
        val charsToDrop = if (itemUrl.endsWith("/")) 1 else 0
        val number = try {
            itemUrl.dropLast(charsToDrop).takeLastWhile { it.isDigit() }.toInt()
        } catch (e: Exception) {
            null
        }
        return number
    }

    fun getSpeciesImageUrl(itemNumber: Int): String {
        return ItemsImageUrl.SPECIES.getImageUrl(itemNumber.toString())
    }

    fun getPlanetImageUrl(itemNumber: Int): String {
        return ItemsImageUrl.PLANET.getImageUrl(itemNumber.toString())
    }


}
