package br.com.vaniala.starwars.ui.lastseen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import br.com.vaniala.starwars.core.State
import br.com.vaniala.starwars.databinding.FragmentLastSeenBinding
import br.com.vaniala.starwars.domain.model.LastSeen
import br.com.vaniala.starwars.ui.lastseen.adapter.LastSeenAdapter
import br.com.vaniala.starwars.ui.lastseen.viewmodel.LastSeenViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 17/04/23.
 *t
 */
@AndroidEntryPoint
class LastSeenFragment : Fragment() {

    private var _binding: FragmentLastSeenBinding? = null
    private val binding: FragmentLastSeenBinding
        get() = _binding!!

    private val viewModel: LastSeenViewModel by viewModels()
    private lateinit var adapter: LastSeenAdapter

    private val findNavController by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLastSeenBinding
            .inflate(
                inflater,
                container,
                false,
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.lastSeen()
        initAdapter()
        setStateHome()
    }

    private fun initAdapter() {
        adapter = LastSeenAdapter()
        binding.fragmentLastSeenRecycler.adapter = adapter
        adapter.onItemClickListener = {
            it.film?.let { film ->
                findNavController.navigate(
                    LastSeenFragmentDirections.actionLastSeenToFilmsDetails(film),
                )
            }
            it.character?.let { character ->
                findNavController.navigate(
                    LastSeenFragmentDirections.actionLastSeenToCharactersDetails(character),
                )
            }
        }
    }

    private fun setStateHome() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.lastSeen.collectLatest { stateFavorite: State<List<LastSeen>> ->
                when (stateFavorite) {
                    is State.Loading -> timber.log.Timber.d("loading")
                    is State.Empty -> timber.log.Timber.d("loading") // showStateEmpty()
                    is State.Error -> timber.log.Timber.d("loading") // showStateError()
                    is State.Success<List<LastSeen>> -> {
                        showStateSuccess(stateFavorite.result)
                    }
                }
//            }
            }
        }
    }

    private fun showStateSuccess(lastSeen: List<LastSeen>) {
        adapter.submitList(lastSeen)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.fragmentLastSeenRecycler.adapter = null
    }
}
