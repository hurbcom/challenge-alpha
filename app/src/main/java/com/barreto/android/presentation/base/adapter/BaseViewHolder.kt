package com.barreto.android.presentation.base.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.subjects.PublishSubject

open class BaseViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {

    var currentItem: T? = null

    val notifyItemClick: PublishSubject<T> = PublishSubject.create()

    open fun bindItem(item: T?) {}
}