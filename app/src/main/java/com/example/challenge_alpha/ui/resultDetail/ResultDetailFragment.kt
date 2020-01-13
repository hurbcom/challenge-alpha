package com.example.challenge_alpha.ui.resultDetail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.challenge_alpha.R
import com.example.challenge_alpha.di.Injectable
import com.example.challenge_alpha.di.injector
import com.example.challenge_alpha.di.viewModel
import com.ms.square.android.expandabletextview.ExpandableTextView
import java.text.NumberFormat
import java.util.*

/**
 * Fragmento responsável por mostrar com detalhes as informações do hotel/pacote.
 * A classe [displayDetail] tem toda a configuração da view.
 * A toolbar está configurada para lidar com favoritos e ação de compartilhamento.
 */

class ResultDetailFragment : Fragment(), Injectable {


    private val resultDetailViewModel by viewModel(this) {
        injector.resultDetailViewModelFactory.create(args.selectedResult)
    }
    private val args: ResultDetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_resultdetail, container, false)

        displayDetail(root)
        setHasOptionsMenu(true)

        return root
    }

    /**
     * Configuração da view.
     */
    private fun displayDetail(view: View) {

        val name: TextView = view.findViewById(R.id.name_detail)
        val stars: RatingBar = view.findViewById(R.id.stars_detail)
        val city: TextView = view.findViewById(R.id.city_detail)
        val price: TextView = view.findViewById(R.id.price_detail)
        val amenitiesTitle: TextView = view.findViewById(R.id.amenitiesTitle_detail)
        val imageGallery: ImageView = view.findViewById(R.id.recyclerGallery_detail)
        val expandableTextView: ExpandableTextView = view.findViewById(R.id.description_detail)

        val amenities: RecyclerView = view.findViewById(R.id.recyclerAmenities_detail)
        val layoutHorizontal =
            StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)
        amenities.layoutManager = layoutHorizontal

        val amenitiesAdapter = AmenitiesAdapter()

        amenities.adapter = amenitiesAdapter
        amenities.hasFixedSize()

        resultDetailViewModel.getResult.observe(this, Observer {
            name.text = it.resultDetail?.name
            stars.rating = if (it.resultDetail?.stars == null) {
                stars.visibility = View.INVISIBLE
                5f
            } else {
                it.resultDetail?.stars!!
            }
            city.text = it.resultDetail?.address?.city

            val priceFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
            priceFormat.currency = Currency.getInstance(it.resultDetail?.price?.currency ?: "BRL")
            val priceResult = priceFormat.format(it.resultDetail?.price?.amount)

            price.text = priceResult

            expandableTextView.text = it.resultDetail?.description

            if (it.resultDetailAmenities.isNotEmpty()) {
                amenitiesTitle.visibility = View.VISIBLE
                amenitiesAdapter.submitList(it.resultDetailAmenities)
            }


            val imageLoad: String? =
                it.resultDetail?.image ?: it.resultDetailGallery.firstOrNull()?.url
            Glide.with(this)
                .load(imageLoad)
                .fitCenter()
                .centerCrop()
                .placeholder(R.drawable.ic_refresh)
                .error(R.drawable.ic_sync_problem)
                .fallback(R.drawable.ic_sync_problem)
                .into(imageGallery)

        })
    }

    /**
     * Configuração da toolbar.
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.resultsfavorites_menu, menu)

        resultDetailViewModel.isFavorited.observe(this, Observer {
            Log.d("menuTag", "$it")

            if (it) {
                menu[0].setIcon(R.drawable.ic_favorite_select)
                menu[0].isChecked = true
            } else {
                menu[0].setIcon(R.drawable.ic_favorite)
                menu[0].isChecked = false
            }

        })


        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {


        R.id.isFavorited -> {

            if (item.isChecked) {
                resultDetailViewModel.deleteFavorite()
            } else {
                resultDetailViewModel.insertFavorite()
            }

            true
        }
        R.id.share -> {

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

            true
        }
        else -> {

            super.onOptionsItemSelected(item)
        }
    }


}