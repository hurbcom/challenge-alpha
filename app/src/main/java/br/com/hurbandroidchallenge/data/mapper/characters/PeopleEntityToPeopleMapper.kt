package br.com.hurbandroidchallenge.data.mapper.characters

import br.com.hurbandroidchallenge.commom.mapper.Mapper
import br.com.hurbandroidchallenge.data.local.model.PeopleEntity
import br.com.hurbandroidchallenge.domain.model.People

class PeopleEntityToPeopleMapper : Mapper<PeopleEntity, People> {
    override fun map(input: PeopleEntity) = input.toPeople()
}