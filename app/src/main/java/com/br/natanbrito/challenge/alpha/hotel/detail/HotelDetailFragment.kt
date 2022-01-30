package com.br.natanbrito.challenge.alpha.hotel.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.transition.TransitionManager
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import coil.load
import com.br.natanbrito.challenge.alpha.R
import com.br.natanbrito.challenge.alpha.databinding.FragmentHotelDetailBinding
import com.br.natanbrito.challenge.alpha.utils.Routes
import com.br.natanbrito.challenge.alpha.utils.customizeText
import com.br.natanbrito.challenge.alpha.utils.gone
import com.br.natanbrito.challenge.alpha.utils.prepareCurrencyText
import com.br.natanbrito.challenge.alpha.utils.setupAmenities
import com.br.natanbrito.challenge.alpha.utils.visible
import com.br.natanbrito.challenge.data.model.results.Gallery
import com.br.natanbrito.challenge.data.model.results.Result
import com.br.natanbrito.challenge.data.utils.COLLAPSED_LINES
import com.br.natanbrito.challenge.data.utils.DIMEN_40
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs

class HotelDetailFragment : Fragment() {

    private lateinit var binding: FragmentHotelDetailBinding
    private lateinit var hotel: Result
    private lateinit var appCompatActivity: AppCompatActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appCompatActivity = activity as AppCompatActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHotelDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            hotel = HotelDetailFragmentArgs.fromBundle(it).hotelResult
            setupLayout(hotel)
        }
    }

    private fun setupLayout(hotel: Result) {
        with(binding) {
            appBar.addOnOffsetChangedListener(
                AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
                    when {
                        abs(verticalOffset) - appBarLayout.totalScrollRange == 0 -> {
                            collapsingToolbarLayout.title = hotel.name
                            counterText.gone()
                        }
                        else -> {
                            enableScroll()
                            collapsingToolbarLayout.title = ""
                            counterText.visible()
                        }
                    }
                }
            )

            setupImageGallery(hotel.gallery)
            setupHotelDetailContainer(hotel)
        }
    }

    private fun setupHotelDetailContainer(hotel: Result) {
        with(binding.hotelDetailContainer) {
            if (hotel.hasFreeCancellation) {
                freeCancellationLabel.visible()
            }

            city.text = hotel.address.city
            hotelName.text = hotel.name
            description.text = hotel.smallDescription
            viewMoreLabel.setOnClickListener {
                toggleReadMoreTextView()
            }
            hotelPrice.prepareCurrencyText(R.string.from_hotel_price, hotel.price)
            hotelAmenities.setupAmenities(hotel.amenities, false)
            hotelAmenities.customizeText(
                getString(R.string.amenities, hotelAmenities.text)
            )
            loadHotelRating(hotel.stars)
        }
    }

    override fun onResume() {
        super.onResume()
        binding.toolbar.setNavigationOnClickListener { appCompatActivity.onBackPressed() }
    }

    private fun loadHotelRating(stars: Int) {
        for (position in 0 until stars) {
            val image = AppCompatImageView(requireContext())
            image.load(ResourcesCompat.getDrawable(resources, R.drawable.ic_star_rate, null))
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.apply {
                width = DIMEN_40
                height = DIMEN_40
                binding.hotelDetailContainer.hotelStars.addView(image, this)
            }
        }
    }

    private fun setupImageGallery(gallery: List<Gallery>) {
        val adapter = ImagesViewPagerAdapter(gallery) {
            Routes.navigateToImageRoute(it.url, hotel.description, binding.root)
        }
        with(binding) {
            imageGallery.adapter = adapter

            imageGallery.registerOnPageChangeCallback(
                object : OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        val currentPosition = position + 1
                        binding.counterText.text = String.format(
                            resources.getString(R.string.image_counter),
                            currentPosition,
                            gallery.size
                        )
                    }
                }
            )
        }
    }

    private fun enableScroll() {
        val params = binding.collapsingToolbarLayout.layoutParams as AppBarLayout.LayoutParams
        params.scrollFlags = (
            AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                or AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
            )
        binding.collapsingToolbarLayout.layoutParams = params
    }

    private fun toggleReadMoreTextView() {
        with(binding.hotelDetailContainer) {

            description.maxLines = if (description.maxLines != Integer.MAX_VALUE) {
                Integer.MAX_VALUE
            } else {
                COLLAPSED_LINES
            }

            viewMoreLabel.text = if (description.maxLines != Integer.MAX_VALUE) {
                getString(R.string.view_more_label)
            } else {
                getString(R.string.view_less_label)
            }

            TransitionManager.beginDelayedTransition(root)
        }
    }
}
