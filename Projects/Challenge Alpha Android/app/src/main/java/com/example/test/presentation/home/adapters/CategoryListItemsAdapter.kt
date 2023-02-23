package com.example.test.presentation.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.test.R
import com.example.test.databinding.ItemListCategoriesBinding
import com.example.test.presentation.models.CategoryItemDetailsViewData
import com.example.test.utils.Extensions.withHelper

class CategoryListItemsAdapter(
    private val context: Context?,
    private var items: List<CategoryItemDetailsViewData>,
    private val callback: (item: CategoryItemDetailsViewData) -> Unit
) : RecyclerView.Adapter<CategoryListItemsAdapter.Holder>() {

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
                context?.run {
                    item.withHelper(this).apply {
                        tvName.text = name
                        imgItem.load(image) {
                            listener(onError = { _, _ -> imgItem.setImageResource(R.drawable.ic_star_wars) })
                        }
                        tvInfoOne.text = infoOne
                        tvInfoTwo.text = infoTwo
                    }
                }
                cardCategory.setOnClickListener { callback.invoke(item) }
            }
        }
    }

    fun setItems(it: List<CategoryItemDetailsViewData>) {
        this.items = it
        notifyDataSetChanged()
    }
}