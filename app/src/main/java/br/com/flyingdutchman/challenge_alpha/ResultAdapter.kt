package br.com.flyingdutchman.challenge_alpha

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import kotlinx.android.synthetic.main.result_item.view.*

class ResultAdapter(private val action: (Result) -> Unit? = {}) :
    RecyclerView.Adapter<ResultAdapter.ViewHolder>() {

    private var items: MutableList<Result> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.result_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = items[position]
        holder.bind(result)

        holder.itemView.custom_view_result_content_root.setOnClickListener {
            action.invoke(result)
        }
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(results: List<Result>) {
        val result = DiffUtil.calculateDiff(SearchResultDiffUtils(results, items))
        items.clear()
        items.addAll(results)
        result.dispatchUpdatesTo(this)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Result) {
            itemView.result_thumb.load(item.gallery[0].url) {
                placeholder(
                    android.R.color.darker_gray
                )
            }
            itemView.result_name.text = item.name
            itemView.result_shot_description.text = item.shortDescription
        }
    }

}