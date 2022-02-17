package com.isranascimento.lastviewed.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.isranascimento.hoteldetail.fragment.HotelDetailFragment
import com.isranascimento.lastviewed.R
import com.isranascimento.lastviewed.databinding.LastViewedFragmentBinding
import com.isranascimento.lastviewed.repository.ILastViewedRepository
import com.isranascimento.lastviewed.ui.adapter.LastViewedHotelListAdapter
import com.isranascimento.lastviewed.ui.models.LastViewedUIState
import com.isranascimento.lastviewed.ui.viewmodel.LastViewedViewModel
import com.isranascimento.theme.hotel.HotelCardItem
import com.isranascimento.utils.extensions.loadFromResource
import com.isranascimento.utils.extensions.navigateToScreen
import com.isranascimento.utils.extensions.setVisible
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LastViewedFragment: Fragment(),
    LastViewedHotelListAdapter.LastViewedHotelListAdapterContract {

    private lateinit var binding: LastViewedFragmentBinding
    private val hotelsAdapter by lazy {
        LastViewedHotelListAdapter(this)
    }
    private val viewModel: LastViewedViewModel by viewModel()
    private val repository: ILastViewedRepository by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return LastViewedFragmentBinding.inflate(
            inflater, container, false
        ).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lastViewedHotelsList.adapter = hotelsAdapter
        setupViewModelObserver()
    }

    private fun setupViewModelObserver() {
        viewModel
            .uiState
            .flowWithLifecycle(lifecycle)
            .onEach {
                renderUi(it)
            }
            .launchIn(lifecycleScope)
    }

    private fun renderUi(state: LastViewedUIState) {
        if(state is LastViewedUIState.WithItem) {
            binding.noHotelGroup.setVisible(false)
            renderSuccess(state.items)
        } else {
            binding.noHotelPlaceholder.loadFromResource(R.drawable.ic_empty)
            binding.noHotelGroup.setVisible(true)
        }
    }

    private fun renderSuccess(items: List<HotelCardItem>) {
        hotelsAdapter.submitList(items)
    }

    override fun onHotelClick(hotelId: String) {
        navigateToScreen(
            HotelDetailFragment.newInstance(
                viewModel.getHotelWithId(hotelId)
            )
        )
    }
}