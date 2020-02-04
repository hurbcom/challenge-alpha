package br.com.flyingdutchman.challenge_alpha.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.flyingdutchman.challenge_alpha.R
import br.com.flyingdutchman.challenge_alpha.commons.*
import br.com.flyingdutchman.challenge_alpha.ui.search.model.SearchResult
import coil.api.load
import kotlinx.android.synthetic.main.result_grid_item.view.*

class SearchResultAdapter(
    private val action: (SearchResult) -> Unit? = {},
    private val layoutManager: GridLayoutManager? = null
) :
    RecyclerView.Adapter<SearchResultAdapter.ViewHolder>() {

    private var items: MutableList<SearchResult> = mutableListOf()

    companion object {
        const val GRID_ITEM_TYPE = 1
        const val LIST_ITEM_TYPE = 0
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        when (viewType) {
            LIST_ITEM_TYPE -> ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.result_list_item,
                    parent,
                    false
                )
            )
            else -> ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.result_grid_item,
                    parent,
                    false
                )
            )
        }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = items[position]
        holder.bind(result)

        holder.itemView.custom_view_result_content_root.setOnClickListener {
            action.invoke(result)
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return if (layoutManager?.spanCount == 1) LIST_ITEM_TYPE
        else GRID_ITEM_TYPE
    }

    fun updateItems(searchResults: List<SearchResult>) {
        val result = DiffUtil.calculateDiff(
            SearchResultDiffUtils(
                searchResults,
                items
            )
        )
        items.clear()
        items.addAll(searchResults)
        result.dispatchUpdatesTo(this)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: SearchResult) {
            itemView.result_thumb.load(item.gallery[0].url) {
                placeholder(
                    android.R.color.darker_gray
                )
            }
            itemView.result_name.text = item.name
            itemView.result_city.text = item.city

            if (item.freeCancelation) {
                itemView.result_free_cancellation.show()
                itemView.result_free_cancellation.text =
                    spannable {
                        color(
                            itemView.resources.getColor(R.color.hurb_green),
                            itemView.resources.getString(R.string.free_cancellation)
                        )
                    }
            }

            if (item.oldPrice == item.currentPrice) {
                itemView.result_old_price.hide()
            }else {
                itemView.result_old_price.show()
                itemView.result_old_price.text =
                    spannable {
                        strike(item.oldPrice)
                    }
            }

            itemView.result_price.text =
                spannable {
                    color(
                        itemView.resources.getColor(R.color.hurb_orange),
                        item.currentPrice
                    )
                }

            itemView.result_rating.rating = item.rating.toFloat()

        }
    }

}