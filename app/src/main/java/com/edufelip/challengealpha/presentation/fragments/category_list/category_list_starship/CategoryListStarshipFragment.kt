package com.edufelip.challengealpha.presentation.fragments.category_list.category_list_starship

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.edufelip.challengealpha.R
import com.edufelip.challengealpha.presentation.base.models.StateUI
import com.edufelip.challengealpha.presentation.fragments.category_list.base.BaseCategoryListFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CategoryListStarshipFragment @Inject constructor(
    val adapter: CategoryListStarshipItemAdapter,
    var mCategoryListStarshipViewModel: CategoryListStarshipViewModel? = null
) : BaseCategoryListFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        setupToolbarTitle()
        setupRecyclerViewAdapter()
        setupViewModel()
        observeItems()
        observeItemsState()
        observeTextInput()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mCategoryListStarshipViewModel?.getItemList()
    }

    private fun setupToolbarTitle() {
        (activity as AppCompatActivity).apply {
            title = getString(R.string.category_list_starship_toolbar_title)
        }
    }

    override fun setupViewModel() {
        mCategoryListStarshipViewModel =
            mCategoryListStarshipViewModel ?: ViewModelProvider(requireActivity()).get(
                CategoryListStarshipViewModel::class.java
            )
    }

    private fun setupRecyclerViewAdapter() {
        binding.categoryListRecyclerView.apply {
            adapter = this@CategoryListStarshipFragment.adapter
        }
    }

    override fun observeTextInput() {
        binding.fragmentCategoryListTextinputedittext.doOnTextChanged { text, _, _, _ ->
            mCategoryListStarshipViewModel?.search(text.toString())
        }
    }

    override fun observeItems() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mCategoryListStarshipViewModel?.starshipList?.collect {
                    adapter.setItems(it)
                }
            }
        }
    }

    override fun observeItemsState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mCategoryListStarshipViewModel?.listItemState?.collect {
                    when (it) {
                        is StateUI.Error -> showErrorToast()
                        is StateUI.Idle -> Unit
                        is StateUI.Processed -> showResult()
                        is StateUI.Processing -> showLoading()
                    }
                }
            }
        }
    }
}