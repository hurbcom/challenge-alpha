package com.br.myapplication.ui.film

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.br.myapplication.databinding.FragmentFilmsBinding
import com.br.myapplication.extensions.hide
import com.br.myapplication.extensions.visible
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilmsFragment: Fragment() {

    private var _binding: FragmentFilmsBinding? = null

    private val binding get() = _binding!!

    private val viewModel: FilmsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFilmsBinding.inflate(inflater, container, false)

        initObservables()

        binding.searchET.doOnTextChanged { text, start, before, count ->
            lifecycleScope.launch {
                viewModel.setFilter(text.toString())
            }
        }

        return binding.root
    }

    private fun initObservables() {

        val adapter = FilmsAdapter {
            viewModel.updateFilm(it)
        }
        binding.filmsListRV.adapter = adapter
        binding.filmsListRV.layoutManager = LinearLayoutManager(requireContext(), VERTICAL, false)
        viewModel.filmsList.observe(viewLifecycleOwner){

            lifecycleScope.launch {

                adapter.submitData(it)
            }
            if (it != null) {
                binding.progressBar.hide()
                binding.filmsListRV.visible()
            }
        }

        viewModel.filteredFilmList.observe(viewLifecycleOwner){

            lifecycleScope.launch {

                adapter.submitData(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}