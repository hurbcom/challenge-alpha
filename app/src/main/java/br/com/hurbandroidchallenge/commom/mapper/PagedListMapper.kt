package br.com.hurbandroidchallenge.commom.mapper

import br.com.hurbandroidchallenge.data.remote.model.base.PagedListResponse
import br.com.hurbandroidchallenge.domain.model.PagedList

interface PagedListMapper<I, O> : Mapper<PagedListResponse<I>, PagedList<O>>