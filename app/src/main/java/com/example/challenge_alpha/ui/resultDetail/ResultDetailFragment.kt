package com.example.challenge_alpha.ui.resultDetail

import android.app.Application
import android.content.Intent
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
import com.example.challenge_alpha.ChallengeApplication
import com.example.challenge_alpha.R
import com.example.challenge_alpha.di.*
import javax.inject.Inject

class ResultDetailFragment : Fragment(), View.OnClickListener, Injectable {


    private val resultDetailViewModel by viewModel(this) {
        injector.resultDetailViewModelFactory.create(args.selectedResult)
    }
    private val args: ResultDetailFragmentArgs by navArgs()
    private lateinit var favoriteButton: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_resultdetail, container, false)

        setListener(root)

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

        resultDetailViewModel.getResult.observe(this, Observer {
            name.text = it.resultDetail?.name
            stars.rating = it.resultDetail?.stars!!
            city.text = it.resultDetail?.address?.city
            price.text = it.resultDetail?.price?.amount.toString()
            description.text = it.resultDetail?.description
            amenitiesAdapter.submitList(it.resultDetailAmenities)
        })

        resultDetailViewModel.isFavorited.observe(this, Observer {
            favoriteButton.isSelected = it
        })


        return root
    }

    private fun setListener(view: View) {
        favoriteButton = view.findViewById(R.id.favorite_detail)
        favoriteButton.setOnClickListener(this)
        val shareButton = view.findViewById<ImageView>(R.id.share_detail)
        shareButton.setOnClickListener(this)

    }

    override fun onClick(view: View) {

        when (view.id) {
            R.id.favorite_detail -> {
                if (favoriteButton.isSelected) {
                    resultDetailViewModel.deleteFavorite()
                } else {
                    resultDetailViewModel.insertFavorite()
                }


            }
            R.id.share_detail -> {

                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(
                        Intent.EXTRA_TEXT,
                        resultDetailViewModel.getResult.value?.resultDetail?.url
                    )
                    type = "text/plain"
                }
                startActivity(
                    sendIntent
                )


            }
        }

    }

}