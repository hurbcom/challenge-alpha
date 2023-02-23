package com.example.test.presentation.home.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.base.data.BaseResult
import com.example.core.base.presentation.BaseViewModel
import com.example.core.base.presentation.BaseViewExtensions.hide
import com.example.core.base.presentation.BaseViewExtensions.hideKeyboard
import com.example.core.base.presentation.BaseViewExtensions.onRightDrawableClicked
import com.example.core.base.presentation.BaseViewExtensions.show
import com.example.core.base.presentation.RecyclerViewPaginatedScrollListener
import com.example.test.R
import com.example.test.databinding.FragmentCategoryBinding
import com.example.test.presentation.home.adapters.CategoryListItemsAdapter
import com.example.test.presentation.models.CategoryItemDetailsViewData
import com.example.test.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoryFragment(private val viewModel: BaseViewModel) : Fragment() {
    private lateinit var binding: FragmentCategoryBinding
    private val categoryAdapter by lazy {
        CategoryListItemsAdapter(context, arrayListOf()) { item -> onItemSelected(item) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupStateListener()
        initView()
    }

    private fun initView() {
        with(binding) {
            val linearLayoutManager = LinearLayoutManager(context)
            listCategoryItems.adapter = categoryAdapter
            listCategoryItems.layoutManager = linearLayoutManager
            listCategoryItems.addOnScrollListener(object :
                RecyclerViewPaginatedScrollListener(linearLayoutManager) {
                override fun loadMoreItems() = viewModel.getNextPage()
                override fun isLastPage(): Boolean = viewModel.isLastPage()
                override fun isLoading(): Boolean = viewModel.isLoading()
            })
            etSearch.addTextChangedListener { viewModel.searchDebounced(it.toString()) }
            etSearch.onRightDrawableClicked { closeSearch() }
        }

        viewModel.getListData()
    }

    private fun setupStateListener() {
        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    is BaseResult.Success -> setSuccessState(it.data as List<CategoryItemDetailsViewData>)
                    is BaseResult.Error -> setErrorState()
                    is BaseResult.Loading -> setLoadingState(it.shouldShowLoading)
                }
            }
        }
    }

    private fun setSuccessState(categories: List<CategoryItemDetailsViewData>) {
        categoryAdapter.setItems(categories)
        with(binding) {
            cardSearch.show()
            listCategoryItems.show()
            progressPage.hide()
            animation.hide()
            ctlError.hide()
        }
    }

    private fun setErrorState() {
        with(binding) {
            cardSearch.hide()
            listCategoryItems.hide()
            progressPage.hide()
            ctlError.show()
            animation.run {
                show()
                setAnimation(R.raw.error)
                playAnimation()
            }
            tvErrorMessage.text = getString(R.string.error_retrieving_data)
            btTryAgain.setOnClickListener { viewModel.getListData(retry = true) }
        }
    }

    private fun setLoadingState(shouldShowAnimation: Boolean) {
        with(binding) {
            ctlError.hide()
            if (shouldShowAnimation) {
                hideKeyboard()
                cardSearch.hide()
                listCategoryItems.hide()
                animation.run {
                    show()
                    setAnimation(R.raw.loading)
                    playAnimation()
                }
            } else
                progressPage.show()
        }
    }

    private fun onItemSelected(category: CategoryItemDetailsViewData) {
        findNavController().navigate(
            R.id.action_homeFragment_to_categoryItemDetailsFragment,
            bundleOf(Constants.CATEGORY_PARAM_KEY to category)
        )
    }

    private fun closeSearch() {
        with(binding.etSearch) {
            if (text.isNotEmpty()) {
                setText("")
                clearFocus()
                hideKeyboard()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(viewModel: BaseViewModel) = CategoryFragment(viewModel)
    }
}