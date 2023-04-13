package br.com.vaniala.starwars.ui.films

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
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
            fragmentsGridError.text = getString(R.string.films_error_films)
        }
    }

    override fun pagingFilter(search: CharSequence) {
        lifecycleScope.launch {
            viewModel.pagingFilter(
                search.toString(),
            ).collectLatest(adapter::submitData)
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
        stateListener()
    }

    private fun stateListener() {
        adapter.addLoadStateListener { loadState ->
            loadState.decideOnState(
                showLoading = {
                    binding.fragmentGridShimmer.isVisible = it
                    stopShimmer()
                },
                showRecycler = {
                    binding.fragmentGridRecycler.isVisible = it
                },
                showEmptyState = {
                    binding.fragmentsGridEmpty.isVisible = it
                },
                showError = {
                    Toast.makeText(
                        context,
                        resources.getString(R.string.films_error_films),
                        Toast.LENGTH_LONG,
                    ).show()
                },
                showErrorThrows = {
                    binding.fragmentsGridError.isVisible = it
                },
            )
        }
    }

    private inline fun CombinedLoadStates.decideOnState(
        showLoading: (Boolean) -> Unit,
        showRecycler: (Boolean) -> Unit,
        showEmptyState: (Boolean) -> Unit,
        showError: () -> Unit,
        showErrorThrows: (Boolean) -> Unit,
    ) {
        showLoading(
            refresh is LoadState.Loading,
        )

        showRecycler(
            source.refresh is LoadState.NotLoading &&
                adapter.itemCount > 0,
        )

        showEmptyState(
            refresh is LoadState.NotLoading &&
                adapter.itemCount == 0 &&
                append.endOfPaginationReached,
        )

        val errorState = source.append as? LoadState.Error
            ?: source.prepend as? LoadState.Error
            ?: append as? LoadState.Error
            ?: prepend as? LoadState.Error

        errorState?.let {
            showError()
        }
        showErrorThrows(refresh is LoadState.Error)
    }

    override fun onDestroyView() {
        binding.fragmentGridRecycler.adapter = null
        super.onDestroyView()
    }
}
