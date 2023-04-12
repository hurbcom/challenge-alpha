package br.com.vaniala.starwars.ui.film

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
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
            viewModel.pagingDataFlow.collectLatest {
                adapter.submitData(it)
            }
        }

        adapter.onItemClickListener = {
            Toast.makeText(context, it.title, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        timber.log.Timber.d("onDestroyView")
    }
//
//    private fun stateListener() {
//        adapter.addLoadStateListener { loadState ->
//            loadState.decideOnState(
//                showLoading = { visible ->
//                    if (visible) View.VISIBLE else View.GONE
//                },
//                showEmptyState = { visible ->
//                    binding.fragmentsFilmsSearchEmptyList.isVisible = visible
//                    binding.fragmentsFilmsSearchRecycler.isVisible = !visible
//                },
//                showError = { message ->
//                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
//                },
//            )
//        }
//    }
//
//    private inline fun CombinedLoadStates.decideOnState(
//        showLoading: (Boolean) -> Unit,
//        showEmptyState: (Boolean) -> Unit,
//        showError: (String) -> Unit,
//    ) {
//        showLoading(refresh is LoadState.Loading)
//
//        showEmptyState(
//            source.append is LoadState.NotLoading &&
//                source.append.endOfPaginationReached &&
//                adapter.itemCount == 0,
//        )
//
//        val errorState = source.append as? LoadState.Error
//            ?: source.prepend as? LoadState.Error
//            ?: source.refresh as? LoadState.Error
//            ?: append as? LoadState.Error
//            ?: prepend as? LoadState.Error
//            ?: refresh as? LoadState.Error
//
//        errorState?.let { showError(it.error.toString()) }
//    }
//
//    private fun loadState() {
//        lifecycleScope.launch {
//            adapter.loadStateFlow.collect { loadState ->
//                val isListEmpty =
//                    loadState.refresh is LoadState.NotLoading && adapter.itemCount == 0
//                binding.fragmentsFilmsSearchEmptyList.isVisible = isListEmpty
//                binding.fragmentsFilmsSearchRecycler.isVisible =
//                    loadState.source.refresh is LoadState.NotLoading ||
//                    loadState.mediator?.refresh is LoadState.NotLoading
//                binding.fragmentsFilmsSearchShimmer.isVisible = loadState.mediator?.refresh is LoadState.Loading
//
//                if (loadState.mediator?.refresh is LoadState.NotLoading) {
//                    binding.fragmentsFilmsSearchShimmer.stopShimmer()
//                }
//
//                val errorState = loadState.source.append as? LoadState.Error
//                    ?: loadState.source.prepend as? LoadState.Error
//                    ?: loadState.append as? LoadState.Error
//                    ?: loadState.prepend as? LoadState.ErrorZX
//                errorState?.let {
//                    Toast.makeText(
//                        context,
//                        " ${it.error}",
//                        Toast.LENGTH_LONG,
//                    ).show()
//                }
//            }
//        }
//    }
}
