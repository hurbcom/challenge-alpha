package com.example.hurb_challenge.app.domain

import com.example.hurb_challenge.app.core.encodeUrl
import com.example.hurb_challenge.app.data.dto.CharacterDto
import com.example.hurb_challenge.app.data.dto.FilmDto
import com.example.hurb_challenge.app.data.dto.StarshipDto
import com.example.hurb_challenge.app.data.dto.VehicleDto
import com.example.hurb_challenge.app.domain.model.CardItem
import com.example.hurb_challenge.app.domain.model.Character
import com.example.hurb_challenge.app.domain.model.Film
import com.example.hurb_challenge.app.domain.model.Starship
import com.example.hurb_challenge.app.domain.model.Vehicle

fun CharacterDto.toDomain(id: String) = Character(
    id,
    name,
    height,
    gender.encodeUrl(),
    birthYear,
    vehiclesUrl.encodeUrl(),
    starshipsUrl.encodeUrl()
)

fun FilmDto.toDomain(id: String) = Film(
    id, title, episode, openingCrawl, director, releaseDate, charactersUrl.encodeUrl()
)

fun VehicleDto.toDomain(id: String) = Vehicle(id, name, model)

fun StarshipDto.toDomain(id: String) = Starship(id, name, model)

fun Vehicle.toCardItem() = CardItem(
    title = name,
    subtitle = model,
    item = this
)

fun Starship.toCardItem() = CardItem(
    title = name,
    subtitle = model,
    item = this
)

fun Film.toCardItem() = CardItem(
    title = title,
    subtitle = releaseDate,
    item = this,
)

fun Character.toCardItem() = CardItem(
    title = name,
    subtitle = gender.encodeUrl(),
    item = this
)

