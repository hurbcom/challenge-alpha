package br.com.vaniala.starwars.ui.characters

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import br.com.vaniala.starwars.R
import br.com.vaniala.starwars.ui.BaseFragment
import br.com.vaniala.starwars.ui.characters.adapter.CharactersAdapter
import br.com.vaniala.starwars.ui.characters.adapter.footer.CharacterLoadStateAdapter
import br.com.vaniala.starwars.ui.characters.viewmodel.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 13/04/23.
 *
 */
@AndroidEntryPoint
class CharactersFragment : BaseFragment<CharacterViewModel>() {

    lateinit var adapter: CharactersAdapter
    private val findNavController by lazy {
        findNavController()
    }

    override fun initViews() {
        binding.apply {
            fragmentsGridSearchEditText.setText(viewModel.filterName.value)
            fragmentGridTextTitle.text = getString(R.string.characters_title)
            fragmentsGridSearchEditText.hint = getString(R.string.characters_search_hint)
            fragmentsGridEmpty.text = getString(R.string.characters_empty_characters)
        }
    }

    override fun pagingFilter(search: CharSequence) {
        lifecycleScope.launch {
            if (search.isEmpty()) {
                binding.fragmentsGridEmpty.isVisible = false
            }
            viewModel.pagingFilter(search.toString()).collectLatest(adapter::submitData)
        }
    }

    override fun initAdapter() {
        adapter = CharactersAdapter()
        binding.fragmentGridRecycler.adapter = adapter
        val footer = CharacterLoadStateAdapter()

        binding.fragmentGridRecycler.adapter = adapter.withLoadStateFooter(
            footer = footer,
        )

        adapter.onItemClickListener = {
            findNavController.navigate(
                CharactersFragmentDirections.actionCharactersToCharactersDetails(it),
            )
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.characters.collectLatest(adapter::submitData)
            }
        }
        showLoadState()
    }

    private fun showLoadState() {
        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { loadState ->

                binding.fragmentGridShimmer.isVisible =
                    viewModel.filterName.value.isEmpty() && adapter.itemCount < 1 && (
                    loadState.refresh is
                    LoadState.Loading || loadState.source.refresh is LoadState.Loading
                    )
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
