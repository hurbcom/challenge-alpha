package br.com.hurbandroidchallenge.data.mapper.categories

import br.com.hurbandroidchallenge.commom.mapper.Mapper
import br.com.hurbandroidchallenge.data.local.model.HomeCategoriesEntity
import br.com.hurbandroidchallenge.domain.model.Categories

class HomeCategoriesEntityToCategoriesMapper : Mapper<HomeCategoriesEntity, Categories> {
    override fun map(input: HomeCategoriesEntity) = input.toCategories()
}