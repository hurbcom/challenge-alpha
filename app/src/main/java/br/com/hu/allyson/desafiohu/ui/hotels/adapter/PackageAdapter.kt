package br.com.hu.allyson.desafiohu.ui.hotels.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.hu.allyson.desafiohu.R
import br.com.hu.allyson.desafiohu.domain.Hotels
import br.com.hu.allyson.desafiohu.ui.hotels.viewHolder.PackageViewHolder
import br.com.hu.allyson.desafiohu.util.DoubleUtils
import br.com.hu.allyson.desafiohu.util.StatesUtil
import br.com.hu.allyson.desafiohu.util.StringUtils
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
        (holder as PackageViewHolder).local_package.text = "${list[position].address.city} - ${StatesUtil.getSigleState(list[position].address.state)}"

        Picasso.get()
            .load(list[position].gallery[0].url)
            .fit()
            .into(holder.image_package)

        holder.package_name.text = list[position].name

        holder.package_description.text = list[position].smallDescription

        holder.price.text = DoubleUtils.formatToBrazilianCurrency(list[position].price.currentPrice)

        holder.package_amenities.text = "Cortesias:\n ${StringUtils.formatAmenities(list[position].amenities)}"

    }

    fun addItens(list: List<Hotels>){
        this.list = list
    }
}