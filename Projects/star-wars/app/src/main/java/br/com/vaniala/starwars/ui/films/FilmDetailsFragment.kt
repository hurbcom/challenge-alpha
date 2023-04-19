package br.com.vaniala.starwars.ui.films

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import br.com.vaniala.starwars.R
import br.com.vaniala.starwars.databinding.FragmentFilmDetailsBinding
import br.com.vaniala.starwars.ui.films.viewmodel.FilmDetailsViewModel
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 12/04/23.
 *
 */
@AndroidEntryPoint
class FilmDetailsFragment : Fragment() {

    private val arguments by navArgs<FilmDetailsFragmentArgs>()
    private val film by lazy {
        arguments.film
    }
    private var _binding: FragmentFilmDetailsBinding? = null
    private val binding: FragmentFilmDetailsBinding get() = _binding!!

    private val viewModel: FilmDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFilmDetailsBinding
            .inflate(
                inflater,
                container,
                false,
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (isLightMode(requireContext())) {
            WindowInsetsControllerCompat(requireActivity().window, view)
                .isAppearanceLightStatusBars =
                false
        }
        super.onViewCreated(view, savedInstanceState)
        viewModel.savedStateHandle["isFavorite"] = film.isFavorite
        showFilm()
        setFavoriteOnClickListener()
        observeFavoriteStatus()
        addLastSeen()
    }

    private fun setFavoriteOnClickListener() {
        binding.fragmentsFilmsDetailsImageFavorite.setOnClickListener {
            lifecycleScope.launch {
                viewModel.favorite(film)
            }
        }
    }

    private fun addLastSeen() {
        viewModel.addLastSeen(film)
    }

    private fun isLightMode(context: Context): Boolean {
        val darkModeFlag = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return darkModeFlag == Configuration.UI_MODE_NIGHT_NO
    }

    private fun showFilm() {
        binding.apply {
            fragmentsFilmsDetailsImage.load(film.image)
            fragmentsFilmsDetailsTitle.text = film.titleFormatted
            fragmentsFilmsDetailsReleaseDate.text = film.releaseDate
            fragmentsFilmsDetailsDirector.text = getString(R.string.films_details_director, film.director)
            fragmentsFilmsDetailsProducer.text = getString(R.string.films_details_director, film.producer)
            fragmentsFilmsDetailsOpeningCrawl.text = film.openingCrawl
        }
    }

    private fun observeFavoriteStatus() {
        lifecycleScope.launch {
            viewModel.isFavorite.collectLatest { isFavorite ->
                updateFavoriteIcon(isFavorite)
            }
        }
    }

    private fun updateFavoriteIcon(isFavorite: Boolean) {
        if (isFavorite) {
            binding.fragmentsFilmsDetailsImageFavorite.setImageResource(R.drawable.ic_action_favorite)
        } else {
            binding.fragmentsFilmsDetailsImageFavorite.setImageResource(R.drawable.ic_action_not_favorite)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        timber.log.Timber.d("onDestroyView")
    }
}
