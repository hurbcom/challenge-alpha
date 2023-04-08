package br.com.hurbandroidchallenge.commom.extension

import br.com.hurbandroidchallenge.domain.model.base.PagedList

fun <T> pagedListOf() = PagedList(
    count = 0,
    next = null,
    previous = null,
    results = emptyList<T>()
)