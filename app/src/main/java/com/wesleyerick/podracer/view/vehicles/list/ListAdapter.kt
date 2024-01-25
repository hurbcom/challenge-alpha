package com.wesleyerick.podracer.view.vehicles.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.wesleyerick.podracer.R
import com.wesleyerick.podracer.data.model.vehicles.Vehicle
import com.wesleyerick.podracer.databinding.ItemListAdapterBinding
import com.wesleyerick.podracer.util.TypeEnum
import com.wesleyerick.podracer.util.getItemListId
import com.wesleyerick.podracer.util.getPhotoUrl
import com.wesleyerick.podracer.util.getSubtitleText


class ListAdapter(
    private val context: Context,
    private val list: List<Vehicle>,
    private val onClick: (id: String) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val IMAGE_ROUNDED_CORNERS = 48
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(
            context,
            ItemListAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClick
        )


    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as ItemViewHolder).bind(list[position])

    private class ItemViewHolder(
        private val context: Context,
        private val itemBinding: ItemListAdapterBinding,
        private val onClick: (id: String) -> Unit
    ) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(
            item: Vehicle,
        ) = with(itemBinding) {

            val requestOptions =
                RequestOptions().transform(CenterCrop(), RoundedCorners(IMAGE_ROUNDED_CORNERS))

            Glide.with(itemBinding.root)
                .load(getPhotoUrl(url = item.url, path = TypeEnum.VEHICLES.path))
                .apply(requestOptions)
                .placeholder(R.drawable.placeholder)
                .into(itemBinding.itemListImage)

            itemListTitleText.text = item.name
            itemListSubtitleFirstText.text = getSubtitleText(
                context = context,
                beforeValue = context.getString(R.string.passengers_subtitle),
                value = item.passengers
            )
            itemListSubtitleSecondText.text = getSubtitleText(
                context = context,
                beforeValue = context.getString(R.string.model_subtitle),
                value = item.model
            )
            itemBinding.root.setOnClickListener {
                onClick.invoke(getItemListId(item.url))
            }
        }
    }
}