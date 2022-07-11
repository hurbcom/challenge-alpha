package com.edufelip.challengealpha.presentation.fragments.category_detail

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.edufelip.challengealpha.R
import com.edufelip.challengealpha.databinding.FragmentCategoryDetailBinding
import com.edufelip.challengealpha.presentation.base.models.StateUI
import com.edufelip.challengealpha.presentation.fragments.general_list.GeneralListMenuFragmentDirections
import kotlinx.coroutines.launch

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

    protected fun showErrorToast() {
        Toast.makeText(requireContext(), R.string.category_detail_add_favorites_error, Toast.LENGTH_SHORT).show()
    }

    protected fun showSuccessToast() {
        Toast.makeText(requireContext(), R.string.category_detail_add_favorites_success, Toast.LENGTH_SHORT).show()
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