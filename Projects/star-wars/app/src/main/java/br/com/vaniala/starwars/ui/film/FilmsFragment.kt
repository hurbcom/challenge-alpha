package br.com.vaniala.starwars.ui.film

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import br.com.vaniala.starwars.R
import br.com.vaniala.starwars.databinding.FragmentFilmsSearchBinding
import br.com.vaniala.starwars.ui.film.adapter.FilmsAdapter
import br.com.vaniala.starwars.ui.film.viewmodel.FilmViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 11/04/23.
 *
 */
@AndroidEntryPoint
class FilmsFragment : Fragment() {

    private var _binding: FragmentFilmsSearchBinding? = null
    private val binding: FragmentFilmsSearchBinding get() = _binding!!
    private lateinit var adapter: FilmsAdapter

    private val viewModel: FilmViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFilmsSearchBinding
            .inflate(
                inflater,
                container,
                false,
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() {
        adapter = FilmsAdapter()
        binding.fragmentsFilmsSearchRecycler.adapter = adapter

        lifecycleScope.launch {
            viewModel.pagingDataFlow.collectLatest(adapter::submitData)
        }

        adapter.onItemClickListener = {
            Toast.makeText(context, it.title, Toast.LENGTH_SHORT).show()
        }
        stateListener()
    }

    private fun stateListener() {
        adapter.addLoadStateListener { loadState ->
            loadState.decideOnState(
                showLoading = {
                    binding.fragmentsFilmsSearchShimmer.isVisible = it
                    stopShimmer()
                },
                showRecycler = {
                    binding.fragmentsFilmsSearchRecycler.isVisible = it
                },
                showEmptyState = {
                    binding.fragmentsFilmsSearchShimmer.isVisible = it
                },
                showError = {
                    Toast.makeText(
                        context,
                        resources.getString(R.string.films_error_films),
                        Toast.LENGTH_LONG,
                    ).show()
                },
                showErrorThrows = {
                    binding.fragmentsFilmsSearchError.isVisible = it
                },
            )
        }
    }

    private fun stopShimmer() {
        if (!binding.fragmentsFilmsSearchShimmer.isVisible) {
            binding.fragmentsFilmsSearchShimmer.stopShimmer()
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
        super.onDestroyView()
        _binding = null
        timber.log.Timber.d("onDestroyView")
    }
}
