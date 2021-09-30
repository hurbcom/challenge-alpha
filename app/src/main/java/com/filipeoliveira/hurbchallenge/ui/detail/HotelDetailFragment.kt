package com.filipeoliveira.hurbchallenge.ui.detail

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.filipeoliveira.hurbchallenge.R
import com.filipeoliveira.hurbchallenge.databinding.FragmentHotelDetailBinding
import com.filipeoliveira.hurbchallenge.ui.UIState
import com.filipeoliveira.hurbchallenge.ui.favorite.HotelFavoriteListViewModel
import com.filipeoliveira.hurbchallenge.ui.model.HotelUI
import com.filipeoliveira.hurbchallenge.ui.utils.SpaceLeftAndRightItemDecoration
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HotelDetailFragment() : Fragment() {

    private lateinit var binding: FragmentHotelDetailBinding
    private lateinit var imageAdapter: RecyclerViewImageAdapter
    private lateinit var hotelUI: HotelUI
    private val viewModel: HotelFavoriteListViewModel by sharedViewModel()
    private var isInFavorite: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHotelDetailBinding.inflate(inflater)
        imageAdapter = RecyclerViewImageAdapter {
            setupCoverImage(it)
        }
        hotelUI = requireArguments().getSerializable(TAG_HOTEL) as HotelUI
        binding.hotelUI = hotelUI

        setupAmenityList()
        setupRecyclerView()
        return binding.root
    }

    private fun setupAmenityList() {
        val amenityList = hotelUI.amenities
        if (amenityList.isEmpty()) {
            binding.fragHotelDetailAmenityContainer.visibility = View.GONE
        } else {
            for (amenity in amenityList) {
                val textView: TextView = getAmenityItemTextView()
                textView.text = amenity.name
                binding.fragHotelDetailAmenityContainer.addView(textView)
            }
        }
    }

    private fun getAmenityItemTextView(): TextView {
        val textView = TextView(requireContext())
        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(
            0,
            8,
            0,
            8
        )
        textView.layoutParams = layoutParams
        return textView
    }

    private fun setupRecyclerView() {
        val lm = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        binding.fragHotelDetailRc.layoutManager = lm
        binding.fragHotelDetailRc.adapter = imageAdapter
        binding.fragHotelDetailRc.addItemDecoration(
            SpaceLeftAndRightItemDecoration(
                spaceRight = 16,
                spaceLeft = 16
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupListeners()
        setImagesToRecyclerView()
        setupCoverImage()
        viewModel.checkIfItsInFavorites(hotelUI = hotelUI)
    }

    private fun setupObservers() {
        viewModel.isInFavorite.observe(viewLifecycleOwner) {
            when (it) {
                is UIState.Success -> {
                    isInFavorite = it.data
                    updateFavoriteButton(isFavorite = it.data)
                }
                else -> {
                    binding.fragHotelDetailFavorite.visibility = View.INVISIBLE
                }
            }
        }

        viewModel.databaseOperation.observe(viewLifecycleOwner) {
            when (it) {
                is UIState.Success -> {
                    isInFavorite = !isInFavorite
                    updateFavoriteButton(isInFavorite)
                    Toast.makeText(requireContext(), it.data, Toast.LENGTH_SHORT).show()
                }
                is UIState.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                else -> {
                }
            }
        }
    }

    private fun updateFavoriteButton(isFavorite: Boolean) {
        if (isFavorite) {
            binding.fragHotelDetailFavorite.setImageDrawable(
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_heart_filled)
            )
        } else {
            binding.fragHotelDetailFavorite.setImageDrawable(
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_heart_outlined)
            )
        }
    }

    private fun setupListeners() {
        binding.fragHotelDetailGoToHurbBtn.setOnClickListener {
            CustomTabsIntent.Builder()
                .build()
                .launchUrl(requireContext(), Uri.parse(hotelUI.url))
        }
        binding.fragHotelDetailBackBtn.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.fragHotelDetailFavorite.setOnClickListener {
            if (isInFavorite) {
                viewModel.removeFromFavorite(hotelUI)
            } else {
                viewModel.addToFavorite(hotelUI)
            }
        }
        binding.fragHotelDetailShare.setOnClickListener {
            onShareClicked()
        }
    }

    private fun onShareClicked() {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, getShareString())
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    private fun getShareString(): String {
        return getString(R.string.share_message, hotelUI.name, hotelUI.url)
    }

    private fun setImagesToRecyclerView() {
        val urlList = hotelUI.images.map {
            it.url
        }

        imageAdapter.setData(urlList)
    }

    private fun setupCoverImage(imageUrl: String = hotelUI.image) {
        Glide.with(requireContext())
            .load(imageUrl)
            .placeholder(R.drawable.ic_image_placeholder)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    collapseToolbar()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

            })
            .into(binding.fragHotelDetailCover)
    }

    private fun collapseToolbar() {
        binding.fragHotelDetailRoot.transitionToEnd()
    }

    companion object {
        const val TAG_HOTEL = "TAG_HOTEL"
    }

}