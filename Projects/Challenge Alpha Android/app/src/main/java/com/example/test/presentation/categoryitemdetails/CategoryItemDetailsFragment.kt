package com.example.test.presentation.categoryitemdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.core.base.presentation.BaseFragment
import com.example.test.R
import com.example.test.databinding.FragmentCategoryItemDetailsBinding
import com.example.test.presentation.models.CategoryItemDetailsViewData
import com.example.test.utils.Extensions.withHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoryItemDetailsFragment : BaseFragment() {
    private lateinit var binding: FragmentCategoryItemDetailsBinding
    private val viewModel: CategoryItemDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryItemDetailsBinding.inflate(inflater, container, false)
        hasToolbar(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.handleArguments(arguments)
        setupStateListener()
    }

    private fun setupStateListener() {
        with(lifecycleScope){
            launch { viewModel.state.collect { setData(it) } }
        }

    }

    private fun setData(item: CategoryItemDetailsViewData) {
        with(binding) {
            context?.run {
                item.withHelper(this).apply {
                    activity?.title = name
                    tvInfoOne.text = infoOne
                    tvInfoTwo.text = infoTwo
                    tvInfoThree.text = infoThree
                    tvInfoFour.text = infoFour
                    imgItem.load(image) {
                        listener(onError = { _, _ -> imgItem.setImageResource(R.drawable.ic_star_wars) })
                    }
                }
            }
        }
    }
}