package com.isranascimento.hotelslist.ui.viewmodels.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.isranascimento.hotelslist.databinding.HotelListFragmentBinding
import com.isranascimento.hotelslist.ui.viewmodels.HotelsListViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HotelListFragment: Fragment() {
    private lateinit var binding: HotelListFragmentBinding
    private lateinit var viewModel: HotelsListViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel.getHotelsList()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return HotelListFragmentBinding.inflate(inflater, container, false)
            .also {
                binding = it
            }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    // awaiting koin dependencies...
                }
            }
        }
    }
}