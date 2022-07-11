package com.edufelip.challengealpha.presentation.fragments.category_list.category_list_people

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edufelip.challengealpha.R
import com.edufelip.challengealpha.databinding.FragmentCategoryListBinding
import com.edufelip.challengealpha.presentation.base.decorations.SpaceStartAndEndItemDecoration
import com.edufelip.challengealpha.presentation.base.decorations.SpaceTopAndBottomItemDecoration
import com.edufelip.challengealpha.presentation.base.decorations.SpacesItemDecoration
import com.edufelip.challengealpha.presentation.base.models.StateUI
import com.edufelip.challengealpha.presentation.fragments.category_list.base.BaseCategoryListFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CategoryListPeopleFragment @Inject constructor(
    val adapter: CategoryListPeopleItemAdapter,
    var mCategoryListPeopleViewModel: CategoryListPeopleViewModel? = null
) : BaseCategoryListFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        setupToolbarTitle()
        setupRecyclerViewAdapter()
        setupRecyclerViewScrollListener()
        setupViewModel()
        observeItems()
        observeItemsState()
        observeTextInput()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mCategoryListPeopleViewModel?.getItemList()
    }

    override fun observeTextInput() {
        binding.fragmentCategoryListTextinputedittext.doOnTextChanged { text, _, _, _ ->
            mCategoryListPeopleViewModel?.search(text.toString())
        }
    }

    private fun setupToolbarTitle() {
        (activity as AppCompatActivity).apply {
            title = getString(R.string.category_list_people_toolbar_title)
        }
    }

    override fun setupViewModel() {
        mCategoryListPeopleViewModel =
            mCategoryListPeopleViewModel ?: ViewModelProvider(requireActivity()).get(
                CategoryListPeopleViewModel::class.java
            )
    }

    override fun observeItemsState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mCategoryListPeopleViewModel?.listItemState?.collect {
                    when (it.getContentIfNotHandled()) {
                        is StateUI.Error -> showErrorToast()
                        is StateUI.Idle -> Unit
                        is StateUI.Processed -> showResult()
                        is StateUI.Processing -> showLoading()
                        else -> Unit
                    }
                }
            }
        }
    }

    override fun observeItems() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mCategoryListPeopleViewModel?.peopleList?.collect {
                    adapter.setItems(it)
                }
            }
        }
    }

    private fun setupRecyclerViewAdapter() {
        binding.categoryListRecyclerView.apply {
            adapter = this@CategoryListPeopleFragment.adapter
        }
    }

    private fun setupRecyclerViewScrollListener() {
        binding.categoryListRecyclerView.addOnScrollListener(object:
            RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (dy > 0) {
                        val visibleItemCount = mLayoutManager.childCount
                        val totalItemCount = mLayoutManager.itemCount
                        val pastVisibleItems = mLayoutManager.findFirstVisibleItemPosition()

                        if((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            mCategoryListPeopleViewModel?.loadMore()
                        }
                    }
                }
            }
        )

    }
}