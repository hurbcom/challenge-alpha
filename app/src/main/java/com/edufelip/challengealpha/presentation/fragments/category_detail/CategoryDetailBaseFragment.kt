package com.edufelip.challengealpha.presentation.fragments.category_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.edufelip.challengealpha.R
import com.edufelip.challengealpha.databinding.FragmentCategoryDetailBinding

abstract class CategoryDetailBaseFragment: Fragment() {

    private var _binding: FragmentCategoryDetailBinding? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoryDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        setupToolbar()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun setupToolbar() {
        (activity as AppCompatActivity).apply {
            setSupportActionBar(binding.categoryDetailToolbar.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            title = requireContext().getString(R.string.category_detail_toolbar_title)
        }
        binding.categoryDetailToolbar.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }
}