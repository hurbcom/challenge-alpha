package com.br.natanbrito.challenge.alpha.hotels_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.br.natanbrito.challenge.alpha.databinding.HotelsFragmentBinding
import com.br.natanbrito.challenge.alpha.utils.gone
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
        initObservers()
    }

    private fun loadDataOnUi(hotelResultList: List<Result>) {
        groupStarsAdapter = GroupStarsAdapter(hotelResultList)
        binding.groupStarsList.adapter = groupStarsAdapter
        groupStarsAdapter.submitList(hotelResultList)
    }

    private fun initObservers() {
        viewModel.hotels.observe(viewLifecycleOwner) { hotel ->
            hotel?.results?.let { hotelResultList ->
                prepareHotelsList()
                val list = hotelResultList.sortedByDescending { it.stars }
                loadDataOnUi(list)
            }
        }
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