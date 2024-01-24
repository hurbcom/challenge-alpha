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
import com.br.myapplication.R
import com.br.myapplication.data.model.Film
import com.br.myapplication.databinding.FragmentFilmsBinding
import com.br.myapplication.extensions.hide
import com.br.myapplication.extensions.visible
import com.br.myapplication.ui.detail.DetailFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilmsFragment : Fragment() {

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


        val adapter = FilmsAdapter(action = {
            viewModel.updateFilm(it)
        },
            callDetail = {
                callDetail(it)
            }
        )
        binding.filmsListRV.adapter = adapter
        binding.filmsListRV.layoutManager = LinearLayoutManager(requireContext(), VERTICAL, false)
        viewModel.filmsList.observe(viewLifecycleOwner) {

            lifecycleScope.launch {

                adapter.submitData(it)
            }
            if (it != null) {
                binding.progressBar.hide()
                binding.filmsListRV.visible()
            }
        }

        viewModel.filteredFilmList.observe(viewLifecycleOwner) {

            lifecycleScope.launch {

                adapter.submitData(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun callDetail(film: Film) {

        val fragmentDetail = DetailFragment()
        val bundle = Bundle().apply {
            putString("first_item", film.title)
            putString("second_item", film.created)
            putString("third_item", film.director)
            putString("fourth_item", film.releaseDate)
            putString("fifth_item", film.producer)
            putString("image", film.image)
        }

        fragmentDetail.arguments = bundle

        val fragmentManager = requireActivity().supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment_activity_main, fragmentDetail)
        transaction.addToBackStack(null)
        transaction.commit()
    }


}