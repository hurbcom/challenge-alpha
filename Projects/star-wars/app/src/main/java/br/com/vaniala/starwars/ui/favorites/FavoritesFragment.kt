package br.com.vaniala.starwars.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import br.com.vaniala.starwars.core.State
import br.com.vaniala.starwars.databinding.FragmentFavoritesBinding
import br.com.vaniala.starwars.domain.model.Favorite
import br.com.vaniala.starwars.ui.favorites.adapter.CharactersFavoriteAdapter
import br.com.vaniala.starwars.ui.favorites.adapter.FilmsFavoriteAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 17/04/23.
 *
 */
@AndroidEntryPoint
class FavoritesFragment : Fragment() {
    private var _binding: FragmentFavoritesBinding? = null
    private val binding: FragmentFavoritesBinding get() = _binding!!
    private lateinit var adapterFilm: FilmsFavoriteAdapter
    private lateinit var adapterCharacter: CharactersFavoriteAdapter

    private val viewModel: FavoritesViewModel by viewModels()

    private val findNavController by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFavoritesBinding
            .inflate(
                inflater,
                container,
                false,
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.favorites()
        initAdapter()
        setStateHome()
    }

    private fun initAdapter() {
        adapterFilm = FilmsFavoriteAdapter()
        adapterCharacter = CharactersFavoriteAdapter()
        binding.fragmentFavoritesFilmRecycler.adapter = adapterFilm
        binding.fragmentFavoritesCharacterRecycler.adapter = adapterCharacter
        adapterFilm.onItemClickListener = {
            findNavController.navigate(
                FavoritesFragmentDirections.actionFavoritesToFilmsDetails(it),
            )
        }

        adapterCharacter.onItemClickListener = {
            findNavController.navigate(
                FavoritesFragmentDirections.actionFavoritesToCharactersDetails(it),
            )
        }
    }

    private fun setStateHome() {
//        viewModel.favorites.onEach { homeState ->
//            when (homeState) {
//                is State.Loading -> timber.log.Timber.d("loading")
//                is State.Empty -> timber.log.Timber.d("loading") // showStateEmpty()
//                is State.Error -> timber.log.Timber.d("loading") // showStateError()
//                is State.Success -> showStateSuccess(homeState.result)
//            }
//        }.launchIn(lifecycleScope)
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.favorites.collectLatest { stateFavorite: State<Favorite> ->
                when (stateFavorite) {
                    is State.Loading -> timber.log.Timber.d("loading")
                    is State.Empty -> timber.log.Timber.d("loading") // showStateEmpty()
                    is State.Error -> timber.log.Timber.d("loading") // showStateError()
                    is State.Success<Favorite> -> {
                        showStateSuccess(stateFavorite.result)
                    }
                }
//            }
            }
        }
    }

    private fun showStateSuccess(favorites: Favorite) {
        adapterFilm.submitList(favorites.films)
        adapterCharacter.submitList(favorites.characters)
    }

    override fun onDestroyView() {
        binding.fragmentFavoritesFilmRecycler.adapter = null
        binding.fragmentFavoritesCharacterRecycler.adapter = null
        super.onDestroyView()
    }
}
