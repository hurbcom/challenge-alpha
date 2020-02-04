package br.com.flyingdutchman.challenge_alpha.ui.search

import androidx.recyclerview.widget.DiffUtil
import br.com.flyingdutchman.challenge_alpha.ui.search.model.SearchResult

class SearchResultDiffUtils(
    private val searchResults: List<SearchResult>,
    private val items: MutableList<SearchResult>
) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return items.size
    }

    override fun getNewListSize(): Int {
        return searchResults.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val freshItem = searchResults[newItemPosition]
        val oldItem = items[oldItemPosition]
        return freshItem.id == oldItem.id
    }


    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val freshItem = searchResults[newItemPosition]
        val oldItem = items[oldItemPosition]
        return freshItem.name == oldItem.name &&
            freshItem.url == oldItem.url &&
            freshItem.description == oldItem.description &&
            freshItem.shortDescription == oldItem.shortDescription
    }
}
