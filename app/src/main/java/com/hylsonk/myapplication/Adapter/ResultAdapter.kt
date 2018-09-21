package com.hylsonk.myapplication.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.hylsonk.myapplication.Model.Result
import com.hylsonk.myapplication.R

class ResultAdapter(internal var context: Context, internal var resultList:List<Result>) : RecyclerView.Adapter<ResultViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        var itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.result_layout,parent,false)
        return ResultViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        holder.stars.text = resultList[position].getStars().toString()+" stars"
        holder.name.text = resultList[position].getName().toString()
        holder.description.text = resultList[position].getDescription().toString()
    }

}