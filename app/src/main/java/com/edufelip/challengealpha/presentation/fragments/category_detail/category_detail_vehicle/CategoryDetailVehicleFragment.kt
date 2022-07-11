package com.edufelip.challengealpha.presentation.fragments.category_detail.category_detail_vehicle

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.edufelip.challengealpha.R
import com.edufelip.challengealpha.data.local.room.models.Favorite
import com.edufelip.challengealpha.presentation.base.models.StateUI
import com.edufelip.challengealpha.presentation.fragments.category_detail.CategoryDetailBaseFragment
import com.edufelip.challengealpha.presentation.fragments.category_detail.CategoryDetailSharedViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class CategoryDetailVehicleFragment @Inject constructor(

) : CategoryDetailBaseFragment() {

    private val args by navArgs<CategoryDetailVehicleFragmentArgs>()
    private var mSharedViewModel: CategoryDetailSharedViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        setupViewModel()
        setupOptionsMenu()
        observeInsertNote()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupArgs()
    }

    private fun setupViewModel() {
        mSharedViewModel = mSharedViewModel ?: ViewModelProvider(requireActivity()).get(
            CategoryDetailSharedViewModel::class.java
        )
    }

    private fun setupOptionsMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.fragment_detail_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.menu_add_favorites -> {
                        mSharedViewModel?.insertFavorite(
                            Favorite(
                                url = args.vehicle.url,
                                name = args.vehicle.name,
                                imageUrl = args.vehicle.imageUrl
                            )
                        )
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun observeInsertNote() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mSharedViewModel?.insertFavoriteState?.collect {
                    when (it) {
                        is StateUI.Error -> showErrorToast()
                        is StateUI.Idle -> Unit
                        is StateUI.Processed -> showSuccessToast()
                        is StateUI.Processing -> Unit
                    }
                }
            }
        }
    }

    private fun setupArgs() {
        val vehicle = args.vehicle
        Glide.with(requireContext()).setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.placeholder)
        ).load(vehicle.imageUrl).into(binding.categoryListItemImageView)
        binding.categoryDetailFieldOne.text =
            "${requireActivity().getString(R.string.category_detail_vehicles_name_string)}: ${vehicle.name}"
        binding.categoryDetailFieldTwo.text =
            "${requireActivity().getString(R.string.category_detail_vehicles_model_string)}: ${vehicle.model}"
        binding.categoryDetailFieldThree.text =
            "${requireActivity().getString(R.string.category_detail_vehicles_cargo_capacity_string)}: ${vehicle.passengers}"
        binding.categoryDetailFieldFour.text =
            "${requireActivity().getString(R.string.category_detail_vehicles_consumables_string)}: ${vehicle.consumables}"
        binding.categoryDetailFieldFive.text =
            "${requireActivity().getString(R.string.category_detail_vehicles_hyperdriving_rate_string)}: ${vehicle.cargoCapacity}"
    }
}