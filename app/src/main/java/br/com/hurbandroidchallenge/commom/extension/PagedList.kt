package br.com.hurbandroidchallenge.commom.extension

import br.com.hurbandroidchallenge.domain.model.base.PagedList

fun <T> pagedListOf(list: List<T>? = null) = PagedList(
    next = null,
    previous = null,
    results = list ifNull emptyList()
)