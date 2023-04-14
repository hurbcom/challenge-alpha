package br.com.hurbandroidchallenge.data.mapper.planets

import br.com.hurbandroidchallenge.commom.mapper.Mapper
import br.com.hurbandroidchallenge.data.local.model.PlanetEntity
import br.com.hurbandroidchallenge.domain.model.Planet

class PlanetEntityToPlanetMapper : Mapper<PlanetEntity, Planet> {
    override fun map(input: PlanetEntity) = input.toPlanet()
}