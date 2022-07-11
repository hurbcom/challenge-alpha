package com.edufelip.challengealpha.presentation.fragments.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.edufelip.challengealpha.R
import com.edufelip.challengealpha.databinding.FragmentFavoritesBinding
import com.edufelip.challengealpha.presentation.base.models.StateUI
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesFragment @Inject constructor(
    val adapter: FavoritesAdapter,
    var mFavoritesViewModel: FavoritesViewModel? = null
) : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        setupToolbar()
        setupViewModel()
        observeFavorites()
        return binding.root
    }

    private fun setupToolbar() {
        (activity as AppCompatActivity).apply {
            setSupportActionBar(binding.favoritesToolbar.toolbar)
            title = getString(R.string.favorites_toolbar_title)
        }
    }

    private fun setupViewModel() {
        mFavoritesViewModel = mFavoritesViewModel ?: ViewModelProvider(requireActivity()).get(
            FavoritesViewModel::class.java
        )
    }

    private fun observeFavorites() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mFavoritesViewModel?.favoritesStateList?.collect {
                    when (it) {
                        is StateUI.Error -> showErrorToast()
                        is StateUI.Idle -> Unit
                        is StateUI.Processed -> {
                            adapter.setItems(it.data)
                        }
                        is StateUI.Processing -> Unit
                    }
                }
            }
        }
    }

    private fun showErrorToast() {
        Toast.makeText(
            requireContext(),
            "Ocorreu um erro ao carregar os favoritos",
            Toast.LENGTH_SHORT
        ).show()
    }
}