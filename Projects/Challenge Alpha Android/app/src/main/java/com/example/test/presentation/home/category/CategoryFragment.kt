package com.example.test.presentation.home.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.base.data.BaseResult
import com.example.core.base.presentation.BaseViewModel
import com.example.core.base.presentation.BaseViewExtensions.hide
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
        CategoryListItemsAdapter(context, arrayListOf()) { item, position ->
            onItemSelected(item, position)
        }
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
        with(binding.listCategoryItems) {
            val linearLayoutManager = LinearLayoutManager(context)
            adapter = categoryAdapter
            layoutManager = linearLayoutManager
            addOnScrollListener(object : RecyclerViewPaginatedScrollListener(linearLayoutManager) {
                override fun loadMoreItems() {
                    viewModel.getNextPage()
                }

                override fun isLastPage(): Boolean = viewModel.isLastPage()
                override fun isLoading(): Boolean = viewModel.isLoading()
            })
        }
        viewModel.getListData()

    }

    private fun setupStateListener() {
        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    is BaseResult.Success -> setSuccessState(it.data as List<CategoryItemDetailsViewData>)
                    is BaseResult.Error -> setErrorState()
                    else -> setLoadingState()
                }
            }
        }
    }

    private fun setSuccessState(categories: List<CategoryItemDetailsViewData>) {
        categoryAdapter.setItems(categories)
        with(binding) {
            listCategoryItems.show()
            animation.hide()
        }
    }

    private fun setErrorState() {

    }

    private fun setLoadingState() {
        with(binding) {
            listCategoryItems.hide()
            animation.run {
                show()
                setAnimation(R.raw.loading)
                playAnimation()
            }
        }
    }

    private fun onItemSelected(category: CategoryItemDetailsViewData, position: Int) {
        findNavController().navigate(
            R.id.action_homeFragment_to_categoryItemDetailsFragment,
            bundleOf(
                Constants.CATEGORY_PARAM_KEY to category,
                Constants.POSITION_PARAM_KEY to position
            )
        )
    }

    companion object {
        @JvmStatic
        fun newInstance(viewModel: BaseViewModel) = CategoryFragment(viewModel)
    }
}