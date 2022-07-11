package com.edufelip.challengealpha.presentation.fragments.category_detail.category_detail_planet

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

class CategoryDetailPlanetFragment @Inject constructor(

) : CategoryDetailBaseFragment() {

    private val args by navArgs<CategoryDetailPlanetFragmentArgs>()
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
                                url = args.planet.url,
                                name = args.planet.name,
                                imageUrl = args.planet.imageUrl
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
                    when (it.getContentIfNotHandled()) {
                        is StateUI.Error -> showErrorToast()
                        is StateUI.Idle -> Unit
                        is StateUI.Processed -> showSuccessToast()
                        is StateUI.Processing -> Unit
                        else -> Unit
                    }
                }
            }
        }
    }

    private fun setupArgs() {
        val planet = args.planet
        Glide.with(requireContext()).setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.placeholder)
        ).load(planet.imageUrl).into(binding.categoryListItemImageView)
        binding.categoryDetailFieldOne.text =
            "${requireActivity().getString(R.string.category_detail_planet_name_string)}: ${planet.name}"
        binding.categoryDetailFieldTwo.text =
            "${requireActivity().getString(R.string.category_detail_planet_climate_string)}: ${planet.climate}"
        binding.categoryDetailFieldThree.text =
            "${requireActivity().getString(R.string.category_detail_planet_gravity_string)}: ${planet.gravity}"
        binding.categoryDetailFieldFour.text =
            "${requireActivity().getString(R.string.category_detail_planet_orbital_period_string)}: ${planet.orbitalPeriod}"
        binding.categoryDetailFieldFive.text =
            "${requireActivity().getString(R.string.category_detail_planet_population_string)}: ${planet.population}"
    }
}