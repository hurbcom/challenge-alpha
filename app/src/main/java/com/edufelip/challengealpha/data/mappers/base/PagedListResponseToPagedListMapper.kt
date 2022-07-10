package com.edufelip.challengealpha.data.mappers.base

import com.edufelip.challengealpha.common.mapper.Mapper
import com.edufelip.challengealpha.common.mapper.NullableListMapper
import com.edufelip.challengealpha.common.mapper.PagedListMapper
import com.edufelip.challengealpha.data.network.base.MetaResponse
import com.edufelip.challengealpha.data.network.base.PagedListResponse
import com.edufelip.challengealpha.domain.models.base.Meta
import com.edufelip.challengealpha.domain.models.base.PagedList

class PagedListResponseToPagedListMapper<I, O>(
    private val metaMapper: Mapper<MetaResponse, Meta>,
    private val listMapper: NullableListMapper<I, O>
) : PagedListMapper<I, O> {

    override fun map(input: PagedListResponse<I>) =
        with(input) {
            PagedList(
                meta = metaMapper.map(meta),
                items = listMapper.map(objects)
            )
        }
}