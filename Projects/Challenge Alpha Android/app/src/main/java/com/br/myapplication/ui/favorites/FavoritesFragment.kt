package com.br.myapplication.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.br.myapplication.databinding.FragmentFavoritesListBinding
import com.br.myapplication.extensions.hide
import com.br.myapplication.extensions.visible
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment: Fragment() {

    private var _binding : FragmentFavoritesListBinding? = null

    private val binding get() = _binding!!

    private val viewModel: FavoritesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesListBinding.inflate(inflater, container, false)
        initObservables()

        viewModel.fetchFavorites()

        return binding.root
    }

    private fun initObservables() {

        viewModel.favoriteList.observe(viewLifecycleOwner){

            binding.filmsListRV.adapter = FavoritesAdapter(it)
            binding.filmsListRV.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

            if (it != null) {
                binding.progressBar.hide()
                binding.filmsListRV.visible()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}