package com.example.test.presentation.home.planets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.base.BaseResult
import com.example.core.base.ViewExtensions.hide
import com.example.core.base.ViewExtensions.show
import com.example.test.R
import com.example.test.databinding.FragmentCategoryBinding
import com.example.test.domain.models.Planet
import com.example.test.presentation.home.adapters.CategoryListItemsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PlanetsFragment : Fragment() {
    private lateinit var binding: FragmentCategoryBinding
    private val adapter by lazy { CategoryListItemsAdapter<Planet>(context, arrayListOf()) }
    private val viewModel: PlanetsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupStateListener()
        initView()
    }

    private fun initView() {
        with(binding) {
            listCategoryItems.adapter = adapter
            listCategoryItems.layoutManager = LinearLayoutManager(context)
        }
        viewModel.getPlanets(1)
    }

    private fun setupStateListener() {
        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    is BaseResult.Success -> setSuccessState(it.data)
                    is BaseResult.Error -> setErrorState()
                    else -> setLoadingState()
                }
            }
        }
    }

    private fun setSuccessState(planets: List<Planet>) {
        adapter.setItems(planets)
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

    companion object {
        @JvmStatic
        fun newInstance() = PlanetsFragment()
    }
}