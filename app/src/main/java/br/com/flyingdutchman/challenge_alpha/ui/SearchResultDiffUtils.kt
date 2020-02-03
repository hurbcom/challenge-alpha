package br.com.flyingdutchman.challenge_alpha.ui

import androidx.recyclerview.widget.DiffUtil

class SearchResultDiffUtils(
    private val results: List<Result>,
    private val items: MutableList<Result>
) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return items.size
    }

    override fun getNewListSize(): Int {
        return results.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val freshItem = results[newItemPosition]
        val oldItem = items[oldItemPosition]
        return freshItem.id == oldItem.id
    }


    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val freshItem = results[newItemPosition]
        val oldItem = items[oldItemPosition]
        return freshItem.name == oldItem.name &&
            freshItem.url == oldItem.url &&
            freshItem.description == oldItem.description &&
            freshItem.shortDescription == oldItem.shortDescription
    }
}
