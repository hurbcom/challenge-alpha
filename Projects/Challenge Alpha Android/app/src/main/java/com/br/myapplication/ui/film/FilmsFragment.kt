package com.br.myapplication.ui.film

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.br.myapplication.databinding.FragmentFilmsBinding
import com.br.myapplication.extensions.hide
import com.br.myapplication.extensions.visible
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

        viewModel.getFilmList()

        initObservables()

        return binding.root
    }

    private fun initObservables() {
        viewModel.filmsList.observe(viewLifecycleOwner){

            binding.filmsListRV.adapter = FilmsAdapter(it)
            binding.filmsListRV.layoutManager = LinearLayoutManager(requireContext(), VERTICAL, false)
            binding.progressBar.hide()
            binding.filmsListRV.visible()

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}