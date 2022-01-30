package com.br.natanbrito.challenge.alpha.hotel.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.br.natanbrito.challenge.alpha.R
import com.br.natanbrito.challenge.alpha.databinding.HotelsFragmentBinding
import com.br.natanbrito.challenge.alpha.utils.Routes
import com.br.natanbrito.challenge.alpha.utils.gone
import com.br.natanbrito.challenge.alpha.utils.hasInternetConnection
import com.br.natanbrito.challenge.alpha.utils.visible
import com.br.natanbrito.challenge.data.model.results.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HotelsFragment : Fragment() {
    private lateinit var binding: HotelsFragmentBinding
    private val viewModel: HotelsViewModel by viewModels()
    private lateinit var groupStarsAdapter: GroupStarsAdapter
    private var oldStarsValue = 0
    private val listOfHotelsList = arrayListOf<List<Result>>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HotelsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarMain.title = getString(R.string.app_name)
        viewModel.prepareDataRequest(requireContext().hasInternetConnection())
        initObservers()
    }

    private fun initObservers() {

        with(viewModel) {
            isConnected.observe(viewLifecycleOwner) { isOnline ->
                if (isOnline) {
                    showLoadingScreen()
                    prepareHotelsList()
                } else {
                    hideLoadingScreen()
                }
            }

            errorMessage.observe(viewLifecycleOwner) { message ->
                setupErrorLayout(message)
            }

            hotels.observe(viewLifecycleOwner) { hotel ->

                if (listOfHotelsList.isNotEmpty()) {
                    listOfHotelsList.clear()
                }
                oldStarsValue = 0

                hotel?.results?.let { hotelResultList ->
                    hideLoadingScreen()
                    setupHotelsList(hotelResultList)
                    loadDataOnUi(listOfHotelsList)
                }
            }
        }
    }

    private fun setupHotelsList(hotelResultList: List<Result>) {
        hotelResultList.sortedByDescending { it.stars }.forEach { result ->
            if (result.stars != oldStarsValue) {
                val hotels = hotelResultList.filter { it.stars == result.stars }
                listOfHotelsList.addAll(listOf(hotels))
                oldStarsValue = result.stars
            }
        }
    }

    private fun hideLoadingScreen() {
        with(binding) {
            loadingList.stopShimmer()
            loadingList.gone()
        }
    }

    private fun showLoadingScreen() {
        with(binding) {
            loadingList.startShimmer()
            loadingList.visible()
        }
    }

    private fun setupErrorLayout(message: Any) {
        with(binding) {
            groupOffline.visible()
            errorMessage.text = when (message) {
                is Int -> requireContext().getString(message)
                else -> message.toString()
            }
            retryButton.setOnClickListener {
                viewModel.prepareDataRequest(requireContext().hasInternetConnection())
            }
        }
    }

    private fun loadDataOnUi(hotelResultList: ArrayList<List<Result>>) {
        groupStarsAdapter = GroupStarsAdapter(hotelResultList) {
            Routes.navigateToDetailsRoute(it, binding.root)
        }
        binding.groupStarsList.adapter = groupStarsAdapter
        groupStarsAdapter.submitList(hotelResultList)
    }

    private fun prepareHotelsList() {
        with(binding) {
            if (groupOffline.isVisible) {
                groupOffline.gone()
            }
            groupStarsList.visible()
            groupStarsList.hasFixedSize()
        }
    }
}
