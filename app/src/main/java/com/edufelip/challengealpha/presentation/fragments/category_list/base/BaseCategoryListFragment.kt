package com.edufelip.challengealpha.presentation.fragments.category_list.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edufelip.challengealpha.R
import com.edufelip.challengealpha.databinding.FragmentCategoryListBinding
import com.edufelip.challengealpha.presentation.base.decorations.SpaceStartAndEndItemDecoration
import com.edufelip.challengealpha.presentation.base.decorations.SpaceTopAndBottomItemDecoration
import com.edufelip.challengealpha.presentation.base.decorations.SpacesItemDecoration
import com.edufelip.challengealpha.presentation.base.utils.hide
import com.edufelip.challengealpha.presentation.base.utils.show

abstract class BaseCategoryListFragment : Fragment() {
    private var _binding: FragmentCategoryListBinding? = null
    protected val binding get() = _binding!!
    lateinit var mLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoryListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        setupToolbar()
        setupRecyclerViewLayoutManager()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupToolbar()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupToolbar() {
        (activity as AppCompatActivity).apply {
            setSupportActionBar(binding.categoryListToolbar.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding.categoryListToolbar.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupRecyclerViewLayoutManager() {
        val spaceSmall = resources.getDimension(R.dimen.padding_or_margin_small).toInt()
        this.mLayoutManager = LinearLayoutManager(binding.root.context, RecyclerView.VERTICAL, false)
        binding.categoryListRecyclerView.apply {
            layoutManager = mLayoutManager
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

    protected fun showResult() {
        binding.categoryListRecyclerView.show()
        binding.categoryListProgress.hide()
    }

    protected fun showLoading() {
        binding.categoryListRecyclerView.hide()
        binding.categoryListProgress.show()
    }

    protected fun showErrorToast() {
        Toast.makeText(
            requireContext(),
            requireActivity().getString(R.string.category_list_error_toast),
            Toast.LENGTH_SHORT
        ).show()
    }

    abstract fun observeTextInput()
    abstract fun setupViewModel()
    abstract fun observeItemsState()
    abstract fun observeItems()
}