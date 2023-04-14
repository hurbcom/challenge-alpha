package br.com.hurbandroidchallenge.data.mapper.planets

import br.com.hurbandroidchallenge.commom.mapper.Mapper
import br.com.hurbandroidchallenge.data.local.model.PlanetEntity
import br.com.hurbandroidchallenge.data.mapper.films.toEntity
import br.com.hurbandroidchallenge.data.remote.model.PlanetDto

class PlanetDtoToEntityMapper : Mapper<PlanetDto,  PlanetEntity> {
    override fun map(input: PlanetDto) = input.toEntity()
}