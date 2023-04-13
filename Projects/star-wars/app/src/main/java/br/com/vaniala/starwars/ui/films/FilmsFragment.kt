package br.com.vaniala.starwars.ui.films

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import br.com.vaniala.starwars.R
import br.com.vaniala.starwars.databinding.FragmentFilmsBinding
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
class FilmsFragment : Fragment() {

    private var _binding: FragmentFilmsBinding? = null
    private val binding: FragmentFilmsBinding get() = _binding!!
    private lateinit var adapter: FilmsAdapter

    private val viewModel: FilmViewModel by viewModels()

    private val findNavController by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFilmsBinding
            .inflate(
                inflater,
                container,
                false,
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentsFilmsSearchEditText.setText(viewModel.filterTitle.value)
        initAdapter()
        initButtonBack()

        binding.apply {
            fragmentsFilmsSearchEditText.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    filterFilm()
                    return@setOnEditorActionListener true
                }
                return@setOnEditorActionListener false
            }

            fragmentsFilmsSearchEditText.setOnKeyListener { _, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    filterFilm()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }
    }

    private fun filterFilm() {
        binding
            .fragmentsFilmsSearchEditText.text?.trim().let { search ->
                if (search != null) {
                    lifecycleScope.launch {
                        viewModel.pagingFilter(
                            search.toString(),
                        ).collectLatest(adapter::submitData)
                    }
                }
            }
    }

    private fun initButtonBack() {
        binding.fragmentFilmsImgButtonBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initAdapter() {
        adapter = FilmsAdapter()
        binding.fragmentFilmsRecycler.adapter = adapter

        filterFilm()

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
                    binding.fragmentFilmsShimmer.isVisible = it
                    stopShimmer()
                },
                showRecycler = {
                    binding.fragmentFilmsRecycler.isVisible = it
                },
                showEmptyState = {
                    binding.fragmentsFilmsEmpty.isVisible = it
                },
                showError = {
                    Toast.makeText(
                        context,
                        resources.getString(R.string.films_error_films),
                        Toast.LENGTH_LONG,
                    ).show()
                },
                showErrorThrows = {
                    binding.fragmentsFilmsError.isVisible = it
                },
            )
        }
    }

    private fun stopShimmer() {
        if (!binding.fragmentFilmsShimmer.isVisible) {
            binding.fragmentFilmsShimmer.stopShimmer()
        } else {
            binding.fragmentFilmsShimmer.startShimmer()
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
        binding.fragmentFilmsRecycler.adapter = null
        _binding = null
        timber.log.Timber.d("onDestroyView")
    }
}
