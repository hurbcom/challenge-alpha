package br.com.flyingdutchman.challenge_alpha.ui.search

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.flyingdutchman.challenge_alpha.App
import br.com.flyingdutchman.challenge_alpha.R
import br.com.flyingdutchman.challenge_alpha.commons.*
import br.com.flyingdutchman.challenge_alpha.data.GroupedResultData
import br.com.flyingdutchman.challenge_alpha.data.ResultData
import coil.api.load
import kotlinx.android.synthetic.main.result_grid_item.view.*
import kotlinx.android.synthetic.main.result_rails_item.view.*


class SearchResultRailsAdapter(private val items: List<GroupedResultData>) :
    RecyclerView.Adapter<SearchResultRailsAdapter.RailsViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RailsViewHolder =
        RailsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.result_rails_item,
                parent,
                false
            )
        ).apply {
            pool = viewPool
        }


    override fun onBindViewHolder(holder: RailsViewHolder, position: Int) {
        val result = items[position]
        holder.bind(result)
    }

    override fun getItemCount(): Int = items.size


    class RailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var pool: RecyclerView.RecycledViewPool? = null

        fun bind(item: GroupedResultData) {

            val title = if (item.name != "1") "${item.name} Estrelas" else "Pacotes"
            val startDrawable = App.instance.getDrawable(
                R.drawable.ic_star_black_24dp
            )

            itemView.search_result_rails_title.text = title

            setupStartDecorationToRailsTitle(item, startDrawable)

            setupInnerRecycler(item)
        }

        private fun setupInnerRecycler(item: GroupedResultData) {
            itemView.search_result_rails_recycler_view.apply {
                pool?.let { setRecycledViewPool(pool) }

                layoutManager =
                    LinearLayoutManager(
                        context,
                        LinearLayoutManager.HORIZONTAL,
                        false
                    ).apply {
                        initialPrefetchItemCount = 4
                    }

                addItemDecoration(SpacesItemDecoration(resources.getDimension(R.dimen.card_margin).toInt()))

                setHasFixedSize(true)
                isNestedScrollingEnabled = false
                fixNestedScrolling()

                adapter = InnerAdapter(item.results)
            }
        }

        private fun setupStartDecorationToRailsTitle(
            item: GroupedResultData,
            startDrawable: Drawable?
        ) {
            if (item.name != "1") {
                itemView.search_result_rails_title.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    null, null, startDrawable, null
                )

            } else {
                itemView.search_result_rails_title.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    null, null, null, null
                )
            }
        }

        private fun RecyclerView.fixNestedScrolling() {
            addOnItemTouchListener(
                object : RecyclerView.OnItemTouchListener {
                    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}
                    override fun onInterceptTouchEvent(
                        rv: RecyclerView, e:
                        MotionEvent
                    ): Boolean {
                        if (e.action == MotionEvent.ACTION_DOWN &&
                            rv.scrollState == RecyclerView.SCROLL_STATE_SETTLING
                        ) {
                            rv.stopScroll()
                        }
                        return false
                    }

                    override fun onRequestDisallowInterceptTouchEvent(
                        disallowIntercept: Boolean
                    ) {
                    }
                })
        }
    }

    class InnerAdapter(private var items: List<ResultData> = mutableListOf()) :
        RecyclerView.Adapter<InnerCardViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerCardViewHolder =
            InnerCardViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.result_grid_item,
                    parent,
                    false
                )
            )

        override fun onBindViewHolder(holder: InnerCardViewHolder, position: Int) {
            val result = items[position]
            holder.bind(result)
        }


        override fun getItemCount(): Int = items.size
    }

    class InnerCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ResultData) {

            itemView.result_thumb.load(item.gallery[0].url) {
                placeholder(
                    android.R.color.darker_gray
                )
            }
            itemView.result_name.text = item.name
            itemView.result_city.text = item.city

            bindFreeCancellation(item)
            bindOldPrice(item)
            bindPrice(item)
        }

        private fun bindFreeCancellation(item: ResultData) {
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
        }

        private fun bindPrice(item: ResultData) {
            itemView.result_price.text =
                spannable {
                    color(
                        itemView.resources.getColor(R.color.hurb_orange),
                        item.currentPrice
                    )
                }

            if (item.rating > 1) {
                itemView.result_rating.rating = item.rating.toFloat()
            } else {
                itemView.result_rating.hide()
            }
        }

        private fun bindOldPrice(item: ResultData) {
            if (item.oldPrice == item.currentPrice ||
                item.oldPrice.contains("0,00")
            ) {
                itemView.result_old_price.hide()
            } else {
                itemView.result_old_price.show()
                itemView.result_old_price.text =
                    spannable {
                        strike(item.oldPrice)
                    }
            }
        }
    }

}