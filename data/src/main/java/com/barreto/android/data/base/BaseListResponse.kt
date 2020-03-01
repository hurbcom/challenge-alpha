package com.barreto.android.data.base

import com.barreto.android.domain.base.BaseListModel

abstract class BaseListResponse<T> (
    var code: Int = 0,

    var msg: String? = null,

    var total: Int = 0
) {

    fun toListModel() : BaseListModel<T> {
        return BaseListModel(
            code,
            msg,
            total,
            parseItems()
        )
    }

    abstract fun parseItems(): List<T>
}