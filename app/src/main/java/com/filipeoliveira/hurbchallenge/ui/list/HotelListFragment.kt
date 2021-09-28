package com.filipeoliveira.hurbchallenge.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.filipeoliveira.hurbchallenge.R
import com.filipeoliveira.hurbchallenge.databinding.FragmentHotelListBinding
import com.filipeoliveira.hurbchallenge.ui.UIState
import com.filipeoliveira.hurbchallenge.ui.detail.HotelDetailFragment
import com.filipeoliveira.hurbchallenge.ui.utils.SpaceItemDecoration
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HotelListFragment : Fragment() {

    private lateinit var binding: FragmentHotelListBinding
    private lateinit var hotelAdapter: HotelListAdapter
    private val viewModel: HotelListViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHotelListBinding.inflate(inflater)

        setupRecyclerView()

        return binding.root
    }

    private fun setupRecyclerView() {
        hotelAdapter = HotelListAdapter(){ hotel, imageView ->
            val bundle = bundleOf(HotelDetailFragment.TAG_HOTEL to hotel)
            findNavController().navigate(R.id.to_hotelDetailFragment, bundle)
        }

        binding.fragHotelListRcv.apply {
            val lm = LinearLayoutManager(requireContext())
            layoutManager = lm
            adapter = hotelAdapter
            addItemDecoration(
                SpaceItemDecoration(
                    spaceTop = 16,
                    spaceBottom = 16,
                    spaceStart = 16,
                    spaceEnd = 16,
                    extraSpaceBetweenItems = 64
                )
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupListeners()
        viewModel.loadHotelList()
    }

    private fun setupListeners() {
        binding.viewError.baseErrorRoot.setOnClickListener {
            viewModel.loadHotelList()
        }
    }

    private fun setupObservers() {
        viewModel.hotelList.observe(viewLifecycleOwner) {
            when (it) {
                is UIState.Success -> {
                    if(it.data.isNotEmpty()){
                        hotelAdapter.setData(it.data)
                        binding.showRecyclerView()
                    } else {
                        binding.showEmptyState()
                    }
                }
                is UIState.Loading -> {
                    binding.showLoading()
                }
                is UIState.Error -> {
                    binding.viewError.baseErrorText.text = getString(it.message)
                    binding.showError()
                }
                else -> {

                }
            }
        }
    }
}