package com.filipeoliveira.hurbchallenge.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.filipeoliveira.hurbchallenge.R
import com.filipeoliveira.hurbchallenge.databinding.FragmentHotelListBinding
import com.filipeoliveira.hurbchallenge.ui.UIState
import com.filipeoliveira.hurbchallenge.ui.detail.HotelDetailFragment
import com.filipeoliveira.hurbchallenge.ui.list.*
import com.filipeoliveira.hurbchallenge.ui.utils.SpaceItemDecoration
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HotelFavoriteListFragment: Fragment() {

    private lateinit var binding: FragmentHotelListBinding
    private lateinit var hotelAdapter: HotelListAdapter
    private val viewModel: HotelFavoriteListViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHotelListBinding.inflate(inflater)

        setupToolbar()
        setupRecyclerView()
        return binding.root
    }

    private fun setupToolbar() {
        binding.fragHotelListAppBar.toolbarTitle.text = getString(R.string.label_favorite)
    }

    private fun setupRecyclerView() {
        hotelAdapter = HotelListAdapter() { hotel ->
            val bundle = bundleOf(HotelDetailFragment.TAG_HOTEL to hotel)
            findNavController().navigate(R.id.action_hotelFavoriteListFragment_to_hotelDetailFragment, bundle)
        }


        val lm = LinearLayoutManager(requireContext())
        binding.fragHotelListRcv.layoutManager = lm
        binding.fragHotelListRcv.adapter = hotelAdapter
        binding.fragHotelListRcv.addItemDecoration(
            SpaceItemDecoration(
                spaceTop = 16,
                spaceBottom = 16,
                spaceStart = 16,
                spaceEnd = 16,
                extraSpaceBetweenItems = 64
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setupObservers()
        viewModel.loadFavoriteHotels()
    }

    private fun setupListeners() {
        binding.viewError.root.setOnClickListener {
            viewModel.loadFavoriteHotels()
        }
    }

    private fun setupObservers() {
        viewModel.hotelList.observe(viewLifecycleOwner){
            when(it){
                is UIState.Loading -> binding.showLoading()
                is UIState.Error -> {
                    binding.viewError.baseErrorText.text = getString(it.message)
                    binding.showError()
                }
                is UIState.Success -> {
                    val list = it.data
                    if (list.isNotEmpty()){
                        hotelAdapter.setData(it.data)
                        binding.showRecyclerView()
                    } else {
                        binding.showEmptyState()
                    }
                }
                else -> {

                }
            }
        }
    }


}