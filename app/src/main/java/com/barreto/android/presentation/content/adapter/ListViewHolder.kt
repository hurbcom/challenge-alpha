package com.barreto.android.presentation.content.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.barreto.android.domain.content.model.ContentItem
import com.barreto.android.R
import com.barreto.android.presentation.base.adapter.BaseViewHolder
import com.squareup.picasso.Picasso
import io.reactivex.Observable

class ListViewHolder(view: View) : BaseViewHolder<ContentItem>(view) {

    private val headerText by lazy { itemView.findViewById<TextView>(R.id.headerText) }
    private val titleText by lazy { itemView.findViewById<TextView>(R.id.titleText) }
    private val descText by lazy { itemView.findViewById<TextView>(R.id.descText) }
    private val thumbnail by lazy { itemView.findViewById<ImageView>(R.id.thumbnail) }

    private var showHeader: Boolean = false

    init {
        itemView.setOnClickListener {
            currentItem?.run {
                notifyItemClick.onNext(this)
            }
        }
    }

    fun bindItem(item: ContentItem?, showHeader: Boolean) {
        this.showHeader = showHeader
        bindItem(item)
    }

    override fun bindItem(item: ContentItem?) {
        super.bindItem(item)

        currentItem = item
        currentItem?.also {
            if (showHeader) {
                headerText.visibility = View.VISIBLE
                headerText.text = when {
                    it.isHotel -> String.format("* %s estrelas *", it.stars)
                    it.isPackage -> "* Pacotes *"
                    else -> "* Outros *"
                }
            } else {
                headerText.visibility = View.GONE
            }

            titleText.text = it.name
            descText.text = it.description
            Picasso.with(itemView.context)
                .load(it.image)
                .noFade()
                .placeholder(R.drawable.ic_image_black_24dp)
                .fit()
                .tag("placholder")
                .into(thumbnail)
        }
    }

    fun getItemClickObservable(): Observable<ContentItem> {
        return notifyItemClick
    }
}