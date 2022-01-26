package com.br.natanbrito.challenge.alpha.hotels_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.br.natanbrito.challenge.alpha.databinding.HotelsFragmentBinding
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View  {
        binding = HotelsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (requireContext().hasInternetConnection()) {
            viewModel.getHotels()
            initObservers()
        }
    }

    private fun initObservers() {
        viewModel.hotels.observe(viewLifecycleOwner) { hotel ->
            var oldStarsValue = 0
            hotel?.results?.let { hotelResultList ->
                prepareHotelsList()
                val listOfList = arrayListOf<List<Result>>()

                hotelResultList.sortedByDescending { it.stars }.forEach { result ->
                    if (result.stars != oldStarsValue ) {
                        val hotels = hotelResultList.filter { it.stars == result.stars }
                        listOfList.addAll(listOf(hotels))
                        oldStarsValue = result.stars
                    }
                }

                loadDataOnUi(listOfList)
            }
        }
    }

    private fun loadDataOnUi(hotelResultList: ArrayList<List<Result>>) {
        groupStarsAdapter = GroupStarsAdapter(hotelResultList)
        binding.groupStarsList.adapter = groupStarsAdapter
        groupStarsAdapter.submitList(hotelResultList)
    }

    private fun prepareHotelsList() {
        with(binding) {
            loadingList.stopShimmer()
            loadingList.gone()
            groupStarsList.visible()
            groupStarsList.hasFixedSize()
        }
    }
}