package br.com.hurbandroidchallenge.data.mapper.characters

import br.com.hurbandroidchallenge.commom.mapper.Mapper
import br.com.hurbandroidchallenge.data.local.model.PeopleEntity
import br.com.hurbandroidchallenge.data.remote.model.PeopleDto

class PeopleDtoToEntityMapper : Mapper<PeopleDto, PeopleEntity> {
    override fun map(input: PeopleDto) = input.toEntity()
}