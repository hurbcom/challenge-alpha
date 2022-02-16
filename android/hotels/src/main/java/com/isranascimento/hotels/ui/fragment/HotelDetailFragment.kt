package com.isranascimento.hotels.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.PagerSnapHelper
import com.isranascimento.core.fragment.BaseToolbarFragment
import com.isranascimento.hotels.R
import com.isranascimento.hotels.databinding.HotelDetailFragmentBinding
import com.isranascimento.coremodels.hotel.Hotel
import com.isranascimento.hotels.repository.IHotelsDetailRepository
import com.isranascimento.hotels.ui.adapter.detail.HotelDetailGalleryAdapter
import com.isranascimento.hotels.ui.models.HotelDetailUI
import com.isranascimento.utils.hotels.createAmenityTextview
import com.isranascimento.utils.extensions.attachSnapHelperWithListener
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class HotelDetailFragment: BaseToolbarFragment() {
    private lateinit var binding: HotelDetailFragmentBinding

    private val hotel by lazy {
        requireArguments().getParcelable<Hotel>(MODEL)!!
    }

    private val uiModel by lazy {
        HotelDetailUI.fromDomainModel(hotel)
    }

    private val repository: IHotelsDetailRepository by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        lifecycleScope.launch {
            repository.insertIntoLastViewed(hotel)
        }

        return HotelDetailFragmentBinding.inflate(inflater, container, false)
            .also {
                binding = it
            }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            hotelName.text = uiModel.name
            hotelDescription.text = uiModel.description
            hotelRating.rating = uiModel.starCount
            hotelLocation.text = getString(R.string.hotel_list_location_text, uiModel.city, uiModel.state)
            hotelId.text = uiModel.id
            gallery.adapter = HotelDetailGalleryAdapter(uiModel.gallery)
        }
        uiModel.amenities.forEach {
            val amenityTextView = createAmenityTextview(requireContext(), it)
            amenityTextView.setTextColor(requireContext().getColor(R.color.text))
            binding.hotelAmenities.addView(amenityTextView)
        }
        setupGalleryItemsText(0)
        setupGallerySnap()
    }

    private fun setupGallerySnap() {
        binding.gallery.attachSnapHelperWithListener(
            PagerSnapHelper(),
        ) {
            setupGalleryItemsText(it)
        }
    }

    private fun setupGalleryItemsText(currentItem: Int) {
        binding.galleryItemsText.text =
            getString(R.string.gallery_items_text, currentItem + 1, uiModel.gallery.count())
    }

    override fun getToolbarTitle(): String = uiModel.name

    override fun getMenuResource(): Int = R.menu.hotels_detail_menu

    override fun onMenuItemClick(menuItem: MenuItem): Boolean {
        if(menuItem.itemId == R.id.action_share) {
            shareHotel()
            return true
        }
        return super.onMenuItemClick(menuItem)
    }

    private fun shareHotel() {
        val i = Intent(Intent.ACTION_SEND)
        i.type = "text/plain"
        i.putExtra(Intent.EXTRA_TEXT, uiModel.shareLink)
        startActivity(Intent.createChooser(i, getString(R.string.share_action)))
    }

    companion object {
        private const val MODEL = "model"

        fun newInstance(model: Hotel): HotelDetailFragment =
            HotelDetailFragment().apply {
                this.arguments = bundleOf(MODEL to model)
            }
    }
}