package com.filipeoliveira.hurbchallenge.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.filipeoliveira.hurbchallenge.R
import com.filipeoliveira.hurbchallenge.databinding.FragmentHotelListBinding
import com.filipeoliveira.hurbchallenge.ui.filter.FilterFragment
import com.filipeoliveira.hurbchallenge.ui.UIState
import com.filipeoliveira.hurbchallenge.ui.detail.HotelDetailFragment
import com.filipeoliveira.hurbchallenge.ui.utils.SpaceItemDecoration
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HotelListFragment : Fragment() {

    private lateinit var binding: FragmentHotelListBinding
    private lateinit var hotelAdapter: HotelListAdapter
    private lateinit var headerAdapter: HeaderSearchAdapter
    private val viewModel: HotelListViewModel by sharedViewModel()
    private var availableFilters: Boolean = false

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
        binding.fragHotelListAppBar.toolbarTitle.text = getString(R.string.label_hotel)
    }

    private fun setupRecyclerView() {
        headerAdapter = HeaderSearchAdapter() {
            onQueryInserted(it)
        }

        hotelAdapter = HotelListAdapter() { hotel ->
            val bundle = bundleOf(HotelDetailFragment.TAG_HOTEL to hotel)
            findNavController().navigate(R.id.to_hotelDetailFragment, bundle)
        }

        val concatAdapter = ConcatAdapter(headerAdapter, hotelAdapter)

        val lm = LinearLayoutManager(requireContext())
        binding.fragHotelListRcv.layoutManager = lm
        binding.fragHotelListRcv.adapter = concatAdapter
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

    private fun onQueryInserted(query: String) {
        viewModel.loadHotelList(query = query)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupListeners()
    }

    private fun setupListeners() {
        binding.viewError.baseErrorRoot.setOnClickListener {
            viewModel.loadHotelList()
        }

        binding.fragHotelListFab.setOnClickListener {
            openFilterFragment()
        }

        binding.fragHotelFilterIndicator.setOnClickListener {
            viewModel.cleanFilters()
            viewModel.loadHotelList()
            hideFilterIndicator()
        }

        binding.fragHotelListRcv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                if (dy > 0 && binding.fragHotelListFab.visibility == View.VISIBLE) {
                    binding.fragHotelListFab.hide();

                } else if (
                    dy < 0 &&
                    binding.fragHotelListFab.visibility != View.VISIBLE &&
                    availableFilters
                ) {
                    binding.fragHotelListFab.show();
                }
            }
        })
    }

    private fun hideFilterIndicator() {
        binding.fragHotelFilterIndicator.visibility = View.GONE
    }

    private fun showFilterIndicator() {
        binding.fragHotelFilterIndicator.visibility = View.GONE
    }

    private fun openFilterFragment() {
        val availableFilters = viewModel.getAvailableFilters()
        val filterBottomSheet = FilterFragment()
        filterBottomSheet.arguments = bundleOf(FilterFragment.TAG_FILTERS to availableFilters)
        filterBottomSheet.show(parentFragmentManager, FilterFragment.TAG)
    }

    private fun setupObservers() {
        viewModel.hotelList.observe(viewLifecycleOwner) {
            when (it) {
                is UIState.Success -> {
                    hotelAdapter.clear()

                    if (it.data.isNotEmpty()) {
                        setHasEnabledFilters()
                        setHasAvailableFilters()
                        hotelAdapter.setData(it.data)
                        binding.showRecyclerView()
                    } else {
                        binding.showEmptyState()
                    }
                }
                is UIState.Loading -> {
                    hotelAdapter.clear()
                    binding.showLoading()
                }
                is UIState.Error -> {
                    hotelAdapter.clear()
                    binding.viewError.baseErrorText.text = getString(it.message)
                    binding.showError()
                }
                else -> {

                }
            }
        }
    }

    private fun setHasEnabledFilters() {
        if (viewModel.hasEnabledFilters()){
            binding.fragHotelFilterIndicator.visibility = View.VISIBLE
        } else {
            binding.fragHotelFilterIndicator.visibility = View.GONE
        }
    }

    private fun setHasAvailableFilters() {
        binding.fragHotelListFab.show()
        availableFilters = true
    }
}