package com.filipeoliveira.hurbchallenge.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.filipeoliveira.hurbchallenge.data.remote.model.HotelRequestResponse
import com.filipeoliveira.hurbchallenge.databinding.FragmentHotelListBinding
import com.filipeoliveira.hurbchallenge.ui.UIState
import com.filipeoliveira.hurbchallenge.ui.utils.SpaceItemDecoration
import com.google.gson.Gson
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
        hotelAdapter = HotelListAdapter()

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
        setupListeners()
        viewModel.loadHotelList()
    }

    private fun setupListeners() {
        viewModel.hotelList.observe(viewLifecycleOwner) {
            when (it) {
                is UIState.Success -> {
                    hotelAdapter.setData(it.data)
                }
                else -> {

                }
            }
        }
    }
}