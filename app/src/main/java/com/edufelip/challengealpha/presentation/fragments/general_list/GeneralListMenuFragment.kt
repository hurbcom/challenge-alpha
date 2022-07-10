package com.edufelip.challengealpha.presentation.fragments.general_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edufelip.challengealpha.R
import com.edufelip.challengealpha.databinding.FragmentGeneralListMenuBinding
import com.edufelip.challengealpha.presentation.base.models.StateUI
import com.edufelip.challengealpha.presentation.base.decorations.SpaceStartAndEndItemDecoration
import com.edufelip.challengealpha.presentation.base.decorations.SpaceTopAndBottomItemDecoration
import com.edufelip.challengealpha.presentation.base.decorations.SpacesItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class GeneralListMenuFragment @Inject constructor(
    val adapter: GeneralListMenuItemAdapter,
    var mGeneralListMenuViewModel: GeneralListMenuViewModel? = null
) : Fragment() {

    private var _binding: FragmentGeneralListMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGeneralListMenuBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        setupToolbar()
        setupRecyclerView()
        setupViewModel()
        observeMenuItems()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mGeneralListMenuViewModel?.getMenuList(requireContext())
    }

    private fun setupToolbar() {
        (activity as AppCompatActivity).apply {
            setSupportActionBar(binding.generalListToolbar.toolbar)
            title = getString(R.string.general_list_toolbar_title)
        }
    }

    private fun setupViewModel() {
        mGeneralListMenuViewModel =
            mGeneralListMenuViewModel ?: ViewModelProvider(requireActivity()).get(
                GeneralListMenuViewModel::class.java
            )
    }

    private fun setupRecyclerView() {
        val spaceSmall = resources.getDimension(R.dimen.padding_or_margin_small).toInt()
        binding.generalListFragmentRecyclerView.apply {
            adapter = this@GeneralListMenuFragment.adapter
            layoutManager = LinearLayoutManager(binding.root.context, RecyclerView.VERTICAL, false)
            addItemDecoration(
                SpacesItemDecoration(
                    spaceSmall,
                    false
                )
            )
            addItemDecoration(
                SpaceTopAndBottomItemDecoration(spaceSmall, 0)
            )
            addItemDecoration(
                SpaceStartAndEndItemDecoration(spaceSmall, true)
            )
        }
    }

    private fun observeMenuItems() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mGeneralListMenuViewModel?.generalListMenuItemsState?.collect {
                    when (it) {
                        is StateUI.Error -> Unit // Error dialog
                        is StateUI.Idle -> Unit
                        is StateUI.Processed -> adapter.setMenuItems(it.data)
                        is StateUI.Processing -> Unit // Processing Dialog
                    }
                }
            }
        }
    }
}