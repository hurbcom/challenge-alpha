package com.example.challenge_alpha.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge_alpha.R
import com.example.challenge_alpha.model.Suggestions

class SearchViewViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val name: TextView = itemView.findViewById(R.id.item_label)

    private var suggestionDetail: Suggestions? = null

    init {
        view.setOnClickListener {

            val navController = findNavController(it)

            var action = HomeFragmentDirections.hotelsToResults(suggestionDetail!!.city!!)
            if (suggestionDetail!!.suggestionType == "hotel") {
                action = HomeFragmentDirections.hotelsToResults(suggestionDetail!!.text!!)
            }

            navController.navigate(action)
        }

    }


    fun bind(result: Suggestions?) {

        if (result != null) {
            loadData(result)
        }

    }

    private fun loadData(result: Suggestions) {
        this.suggestionDetail = result

        name.text = result.text

    }


    companion object {
        fun create(parent: ViewGroup): SearchViewViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.viewholder_searchview, parent, false)
            return SearchViewViewHolder(view)
        }
    }
}