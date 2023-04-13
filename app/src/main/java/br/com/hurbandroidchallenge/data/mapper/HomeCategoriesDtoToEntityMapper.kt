package br.com.hurbandroidchallenge.data.mapper

import br.com.hurbandroidchallenge.commom.mapper.Mapper
import br.com.hurbandroidchallenge.data.local.model.HomeCategoriesEntity
import br.com.hurbandroidchallenge.data.remote.model.HomeCategoriesDto

class HomeCategoriesDtoToEntityMapper : Mapper<HomeCategoriesDto, List<HomeCategoriesEntity>> {
    override fun map(input: HomeCategoriesDto) = input.toEntity()
}