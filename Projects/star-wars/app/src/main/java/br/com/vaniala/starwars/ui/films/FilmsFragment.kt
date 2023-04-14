package br.com.vaniala.starwars.ui.films

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import br.com.vaniala.starwars.R
import br.com.vaniala.starwars.ui.BaseFragment
import br.com.vaniala.starwars.ui.films.adapter.FilmsAdapter
import br.com.vaniala.starwars.ui.films.viewmodel.FilmViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 11/04/23.
 *
 */
@ExperimentalPagingApi
@AndroidEntryPoint
class FilmsFragment : BaseFragment<FilmViewModel>() {

    private lateinit var adapter: FilmsAdapter
    private val findNavController by lazy {
        findNavController()
    }

    override fun initViews() {
        binding.apply {
            fragmentsGridSearchEditText.setText(viewModel.filterTitle.value)
            fragmentGridTextTitle.text = getString(R.string.films_title)
            fragmentsGridSearchEditText.hint = getString(R.string.films_search_hint)
            fragmentsGridEmpty.text = getString(R.string.films_empty_films)
        }
    }

    override fun pagingFilter(search: CharSequence) {
        lifecycleScope.launch {
            viewModel.pagingFilter(search.toString())
        }
    }

    override fun initAdapter() {
        adapter = FilmsAdapter()
        binding.fragmentGridRecycler.adapter = adapter

        adapter.onItemClickListener = {
            findNavController.navigate(
                FilmsFragmentDirections.actionFilmsToFilmsDetails(it),
            )
        }

        lifecycleScope.launch {
            viewModel.films.collectLatest(adapter::submitData)
        }

        showLoadState()
    }

    private fun showLoadState() {
        lifecycleScope.launch {
            adapter.loadStateFlow.collect { loadState ->

                binding.fragmentGridShimmer.isVisible = loadState.refresh is LoadState.Loading
                binding.fragmentGridRecycler.isVisible = loadState.refresh is LoadState.NotLoading
                binding.fragmentsGridEmpty.isVisible =
                    adapter.itemCount == 0 && loadState.refresh is LoadState.NotLoading

                val errorState = loadState.refresh as? LoadState.Error
                    ?: loadState.source.refresh as? LoadState.Error

                errorState?.let {
                    Toast.makeText(context, resources.getText(R.string.grid_error), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        binding.fragmentGridRecycler.adapter = null
        super.onDestroyView()
    }
}
