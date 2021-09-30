package com.filipeoliveira.hurbchallenge.ui.filter

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.filipeoliveira.hurbchallenge.R
import com.filipeoliveira.hurbchallenge.databinding.FragmentFilterBinding
import com.filipeoliveira.hurbchallenge.ui.list.HotelListViewModel
import com.filipeoliveira.hurbchallenge.ui.model.FilterUI
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FilterFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentFilterBinding
    private var filters: FilterUI? = null
    private val viewModel: HotelListViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilterBinding.inflate(inflater)

        filters = requireArguments().getSerializable(TAG_FILTERS) as? FilterUI

        setupChips()

        return binding.root
    }

    private fun setupChips() {
        if (filters != null) {
            for (amenity in filters!!.amenities) {
                val chip = Chip(requireContext())
                chip.text = amenity.name
                chip.ellipsize = TextUtils.TruncateAt.END
                chip.setChipBackgroundColorResource(R.color.selector_secondary_bg)
                chip.setTextAppearanceResource(R.style.Chip_Text)
                if (viewModel.isOnEnabledFilterList(amenity.name)){
                    chip.isSelected = true
                }
                chip.setOnClickListener {
                    it.isSelected = !it.isSelected
                    val filterName = amenity.name
                    if (viewModel.isOnEnabledFilterList(filterName)) {
                        viewModel.removeFilter(filterName)
                    } else {
                        viewModel.addFilter(filterName)
                    }
                }
                binding.filterChipGroup.addView(chip)
            }
        }
    }

    override fun onStop() {
        viewModel.loadHotelList()
        super.onStop()
    }

    companion object {
        const val TAG = "FilterFragment"
        const val TAG_FILTERS = "TAG_FILTERS"
    }
}