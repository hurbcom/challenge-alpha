package com.edufelip.challengealpha.data.mappers.category_list

import com.edufelip.challengealpha.BuildConfig
import com.edufelip.challengealpha.common.mapper.Mapper
import com.edufelip.challengealpha.data.network.base.extractIdFromUrl
import com.edufelip.challengealpha.data.network.models.FilmResponse
import com.edufelip.challengealpha.domain.models.Film

class FilmResponseToEntityMapper : Mapper<FilmResponse, Film> {
    override fun map(input: FilmResponse): Film =
        with(input) {
            val id = extractIdFromUrl(url)
            return Film(
                id,
                characters,
                director,
                episodeId,
                openingCrawl,
                planets,
                producer,
                releaseDate,
                species,
                starships,
                url,
                title,
                vehicles,
                "${BuildConfig.BASE_IMAGE_URL}/films/${id}.jpg"
            )
        }
}