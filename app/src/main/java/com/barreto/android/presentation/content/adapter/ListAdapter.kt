package com.barreto.android.presentation.content.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.barreto.android.domain.content.model.ContentItem
import com.barreto.android.R
import com.barreto.android.presentation.base.adapter.PagedAdapter
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class ListAdapter : PagedAdapter<ContentItem, ListViewHolder>(DIFF_CALLBACK) {

    private val notifyClick: PublishSubject<Pair<Int, ContentItem>> = PublishSubject.create()

    override fun createItemView(parent: ViewGroup): ListViewHolder {

        return ListViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_content, parent, false)
        ).apply {
            getItemClickObservable().subscribe {
                notifyClick.onNext(Pair(adapterPosition, it))
            }
        }
    }

    override fun bindItemView(holder: ListViewHolder, position: Int) {

        holder.bindItem(getItem(position), shouldShowHeader(position))
    }

    private fun shouldShowHeader(position: Int): Boolean {
        return if(position > 0) {
            try {
                getItem(position).stars != getItem(position - 1).stars
            } catch (error: NullPointerException) {
                false
            }
        } else {
            true
        }
    }

    fun getNotifyItemClick(): Observable<Pair<Int, ContentItem>> {
        return notifyClick
    }

    private companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ContentItem>() {

            override fun areContentsTheSame(oldItem: ContentItem, newItem: ContentItem): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: ContentItem, newItem: ContentItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}