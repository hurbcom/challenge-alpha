package br.com.hu.allyson.desafiohu.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.hu.allyson.desafiohu.R
import br.com.hu.allyson.desafiohu.domain.Hotels
import br.com.hu.allyson.desafiohu.viewHolder.PackageViewHolder
import com.squareup.picasso.Picasso

class PackageAdapter(val context: Context, var list: List<Hotels>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.row_package, parent, false)
        return PackageViewHolder(context, view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PackageViewHolder).local_package.text = list[position].address.city
        Picasso.get()
            .load(list[position].gallery[0].url)
            .fit()
            .into(holder.image_package)

    }

    fun addItens(list: List<Hotels>){
        this.list = list
    }
}