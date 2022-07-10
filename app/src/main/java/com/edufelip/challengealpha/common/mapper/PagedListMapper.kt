package com.edufelip.challengealpha.common.mapper

import com.edufelip.challengealpha.data.network.base.PagedListResponse
import com.edufelip.challengealpha.domain.models.base.PagedList

interface PagedListMapper<I, O> : Mapper<PagedListResponse<I>, PagedList<O>>