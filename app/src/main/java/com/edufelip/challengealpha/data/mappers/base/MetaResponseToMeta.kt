package com.edufelip.challengealpha.data.mappers.base

import com.edufelip.challengealpha.common.mapper.Mapper
import com.edufelip.challengealpha.data.network.base.MetaResponse
import com.edufelip.challengealpha.domain.models.base.Meta

class MetaResponseToMeta : Mapper<MetaResponse, Meta> {

    override fun map(input: MetaResponse) =
        with(input) {
            Meta(
                limit = limit,
                next = next,
                offset = offset,
                total = totalCount
            )
        }
}