package com.example.test.presentation.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.ImageRequest
import coil.size.Scale
import com.example.core.base.Extensions.withDots
import com.example.test.R
import com.example.test.databinding.ItemListCategoriesBinding
import com.example.test.domain.models.Person
import com.example.test.domain.models.Planet
import com.example.test.domain.models.Starship
import com.example.test.utils.ImageHelper

class CategoryListItemsAdapter<T>(
    private val context: Context?,
    private var items: List<T>
) : RecyclerView.Adapter<CategoryListItemsAdapter<T>.Holder>() {

    inner class Holder(val binding: ItemListCategoriesBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemListCategoriesBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return Holder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        items[position].let { item ->
            with(holder.binding) {
                var image = ""
                when (item) {
                    is Person -> {
                        image = ImageHelper.getPeopleImage(position + 1)
                        tvName.text = item.name
                        tvInfoOne.text = "${context?.getString(R.string.height)}: ${item.height} cm"
                        tvInfoTwo.text = "${context?.getString(R.string.weight)}: ${item.mass} kg"
                    }
                    is Planet -> {
                        image = ImageHelper.getPlanetsImage(position + 1)
                        tvName.text = item.name
                        tvInfoOne.text = "${context?.getString(R.string.climate)}: ${item.climate}"
                        tvInfoTwo.text =
                            "${context?.getString(R.string.population)}: ${item.population}"
                    }
                    is Starship -> {
                        image = ImageHelper.getStarshipsImage(position + 1)
                        tvName.text = item.name
                        tvInfoOne.text =
                            "${context?.getString(R.string.type)}: ${item.starshipClass}"
                        tvInfoTwo.text =
                            "${context?.getString(R.string.passengers)}: ${item.passengers}"
                    }
                }
                imgItem.load(image) {
                    listener(onError = { _, _ -> imgItem.setImageResource(R.drawable.ic_star_wars) })
                }
            }
        }
    }

    fun setItems(it: List<T>) {
        this.items = it
        notifyDataSetChanged()
    }
}