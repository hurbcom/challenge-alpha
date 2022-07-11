package com.edufelip.challengealpha.data.mappers.base

import com.edufelip.challengealpha.common.mapper.ListMapper
import com.edufelip.challengealpha.common.mapper.Mapper
import com.edufelip.challengealpha.common.mapper.PagedListMapper
import com.edufelip.challengealpha.data.network.base.PagedListResponse
import com.edufelip.challengealpha.domain.models.base.PagedList

class PagedListResponseToPagedListMapper<I, O>(
    private val listMapper: ListMapper<I, O>
) : PagedListMapper<I, O> {

    override fun map(input: PagedListResponse<I>) =
        with(input) {
            PagedList(
                next = next,
                results = listMapper.map(objects)
            )
        }
}