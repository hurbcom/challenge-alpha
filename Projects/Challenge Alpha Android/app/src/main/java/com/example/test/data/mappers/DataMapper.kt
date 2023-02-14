package com.example.test.data.mappers

import com.example.test.data.datasources.network.models.*
import com.example.test.domain.models.*

object DataMapper {
    fun DataResponse.map(): HeroesData = HeroesData(
        count = count,
        limit = limit,
        offset = offset,
        heroes = heroes?.map { it.map() },
        total = total
    )

    fun HeroResponse.map(): Hero = Hero(
        comics = comics?.map() as? Acting<NormalItem>,
        description = description,
        events = events?.map() as? Acting<NormalItem>,
        id = id,
        modified = modified,
        name = name,
        resourceURI = resourceURI,
        series = series?.map() as? Acting<NormalItem>,
        stories = stories?.map() as? Acting<StoriesItem>,
        thumbnail = thumbnail?.map(),
        urls = urls?.map { it.map() }

    )

    fun ActingResponse<*>.map(): Acting<Item> = Acting(
        available = available,
        collectionURI = collectionURI,
        items = items?.map {
            when (it) {
                is StoriesItemResponse -> StoriesItem(it.name, it.resourceURI, it.type)
                else -> NormalItem(it.name, it.resourceURI)
            }
        },
        returned = returned
    )

    fun ThumbnailResponse.map(): Thumbnail = Thumbnail(
        extension = extension,
        path = path
    )

    fun UrlResponse.map(): Url = Url(type, url)
}