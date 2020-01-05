package com.example.challenge_alpha.ui.resultDetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.challenge_alpha.R

class ResultDetailFragment : Fragment() {

    private lateinit var resultDetailViewModel: ResultDetailViewModel
    private val args: ResultDetailFragmentArgs by navArgs()
    private val TAG = "HurbCall"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        resultDetailViewModel = ViewModelProvider(this).get(ResultDetailViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_resultdetail, container, false)


        val name: TextView = root.findViewById(R.id.name_detail)
        val stars: RatingBar = root.findViewById(R.id.stars_detail)
        val city: TextView = root.findViewById(R.id.city_detail)
        val price: TextView = root.findViewById(R.id.price_detail)
        val description: TextView = root.findViewById(R.id.description_detail)

        val amenities: RecyclerView = root.findViewById(R.id.recyclerAmenities_detail)
        val layoutHorizontal =
            StaggeredGridLayoutManager(3, LinearLayout.HORIZONTAL)
        amenities.layoutManager = layoutHorizontal

        val amenitiesAdapter = AmenitiesAdapter()

        amenities.adapter = amenitiesAdapter

        resultDetailViewModel.getResult(args.selectedResult)
        resultDetailViewModel.getResult.observe(this, Observer {
            name.text = it.resultDetail?.name
            stars.rating = it.resultDetail?.stars!!
            city.text = it.resultDetail?.address?.city
            price.text = it.resultDetail?.price?.amount.toString()
            description.text = it.resultDetail?.description
            amenitiesAdapter.submitList(it.resultDetailAmenities)
        })


        return root
    }

}