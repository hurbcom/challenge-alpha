package com.br.myapplication.ui.species

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.br.myapplication.databinding.FragmentSpeciesBinding
import com.br.myapplication.extensions.hide
import com.br.myapplication.extensions.visible
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SpeciesFragment: Fragment() {

    private var _binding: FragmentSpeciesBinding? = null

    private val binding get() = _binding!!

    private val viewModel: SpeciesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSpeciesBinding.inflate(inflater, container, false)

        initObservables()

        binding.searchET.doOnTextChanged { text, start, before, count ->
            lifecycleScope.launch {
                viewModel.setFilter(text.toString())
            }
        }

        return binding.root
    }

    private fun initObservables() {

        val adapter = SpeciesAdapter {
            viewModel.updateSpecie(it)
        }
        binding.speciesListRV.adapter = adapter
        binding.speciesListRV.layoutManager = LinearLayoutManager(requireContext(), VERTICAL, false)
        viewModel.specieList.observe(viewLifecycleOwner){

            lifecycleScope.launch {

                adapter.submitData(it)
            }
            if (it != null) {
                binding.progressBar.hide()
                binding.speciesListRV.visible()
            }
        }

        viewModel.filteredSpecieList.observe(viewLifecycleOwner){

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