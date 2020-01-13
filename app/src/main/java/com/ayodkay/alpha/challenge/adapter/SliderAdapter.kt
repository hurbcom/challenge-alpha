package com.ayodkay.alpha.challenge.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.ayodkay.alpha.challenge.R
import com.smarteist.autoimageslider.SliderViewAdapter
import com.squareup.picasso.Picasso
import java.text.FieldPosition

class SliderAdapter(private val name: String, private val imageList : ArrayList<ArrayList<String>>,private val currentPosition: Int) :
    SliderViewAdapter<SliderAdapter.SliderAdapterVH>() {


    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
        val inflate: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.image_slider_layout_item, null)
        return SliderAdapterVH(inflate)
    }

    override fun onBindViewHolder(
        viewHolder: SliderAdapterVH,
        position: Int
    ) {

        viewHolder.textViewDescription.text = name
        when (position) {
            0 -> Picasso
                .get()
                .load(imageList[currentPosition][position])
                .into(viewHolder.imageViewBackground)
            1 -> Picasso
                .get()
                .load(imageList[currentPosition][position])
                .into(viewHolder.imageViewBackground)
            2 -> Picasso
                .get()
                .load(imageList[currentPosition][position])
                .into(viewHolder.imageViewBackground)
            3 -> Picasso
                .get()
                .load(imageList[currentPosition][position])
                .into(viewHolder.imageViewBackground)

            4 -> Picasso
                .get()
                .load(imageList[currentPosition][position])
                .into(viewHolder.imageViewBackground)
        }
    }

    override fun getCount(): Int { //slider view count could be dynamic size
        return 5
    }

    inner class SliderAdapterVH(itemView: View) :
        ViewHolder(itemView) {
        var imageViewBackground: ImageView = itemView.findViewById(R.id.iv_auto_image_slider)
        var textViewDescription: TextView = itemView.findViewById(R.id.tv_auto_image_slider)

    }

}