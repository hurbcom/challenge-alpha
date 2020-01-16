package com.ayodkay.alpha.challenge.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.ayodkay.alpha.challenge.App
import com.ayodkay.alpha.challenge.App.Companion.appContext
import com.ayodkay.alpha.challenge.R
import com.smarteist.autoimageslider.SliderViewAdapter
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import okhttp3.OkHttpClient
import okhttp3.Protocol
import java.util.*
import kotlin.collections.ArrayList


class SliderAdapter(private val name: String, private val imageList : ArrayList<ArrayList<String>>,private val currentPosition: Int? = null) :
    SliderViewAdapter<SliderAdapter.SliderAdapterVH>() {


    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(
        parent: ViewGroup): SliderAdapterVH 
    {
        val inflate: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.image_slider_layout_item, null)
        return SliderAdapterVH(inflate)
    }

    override fun onBindViewHolder(
        viewHolder: SliderAdapterVH,
        position: Int)
    {

        viewHolder.textViewDescription.text = name
        if (currentPosition != null){
            when (position) {
                0 -> Picasso
                    .get()
                    .load(imageList[currentPosition][position])
                    .error(R.drawable.ic_launcher_background)
                    .into(viewHolder.imageViewBackground)
                1 -> Picasso
                    .get()
                    .load(imageList[currentPosition][position])
                    .error(R.drawable.ic_launcher_background)
                    .into(viewHolder.imageViewBackground)
                2 -> Picasso
                    .get()
                    .load(imageList[currentPosition][position])
                    .error(R.drawable.ic_launcher_background)
                    .into(viewHolder.imageViewBackground)
                3 -> Picasso
                    .get()
                    .load(imageList[currentPosition][position])
                    .error(R.drawable.ic_launcher_background)
                    .into(viewHolder.imageViewBackground)

                4 -> Picasso
                    .get()
                    .load(imageList[currentPosition][position])
                    .error(R.drawable.ic_launcher_background)
                    .into(viewHolder.imageViewBackground)
            }
        }
    }

    override fun getCount(): Int {
        //slider view count could be dynamic size
        return 5
    }

    inner class SliderAdapterVH(itemView: View) :
        ViewHolder(itemView) 
    {
        var imageViewBackground: ImageView = itemView.findViewById(R.id.iv_auto_image_slider)
        var textViewDescription: TextView = itemView.findViewById(R.id.tv_auto_image_slider)

    }

}