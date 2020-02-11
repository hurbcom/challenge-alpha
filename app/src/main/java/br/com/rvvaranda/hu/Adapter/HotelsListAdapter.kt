package br.com.rvvaranda.hu.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.rvvaranda.hu.Adapter.Holders.HotelsListHolder
import br.com.rvvaranda.hu.Model.Hotel
import br.com.rvvaranda.hu.R


class HotelsListAdapter(
    var lstHotels: MutableList<Hotel>) :
    RecyclerView.Adapter<HotelsListHolder>() {


    companion object {
        var currentStar = 0
    }

    override fun getItemCount(): Int {
        return lstHotels.count()
    }

    override fun onBindViewHolder(holder: HotelsListHolder, position: Int) {
        val local = lstHotels.get(position)
        holder.bindData(local)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelsListHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.activity_hotels_list_adapter,
            parent,
            false
        ) as View


        return HotelsListHolder(itemView)
    }

    fun addItens(lstNewItens: List<Hotel>) {
        lstHotels.addAll(lstNewItens)
        notifyDataSetChanged()
    }

    fun clearData() {
        lstHotels.clear()
        currentStar = 0
        notifyDataSetChanged()
    }


}