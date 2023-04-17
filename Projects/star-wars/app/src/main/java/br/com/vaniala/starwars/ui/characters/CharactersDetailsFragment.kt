package br.com.vaniala.starwars.ui.characters

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
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.vaniala.starwars.R
import br.com.vaniala.starwars.databinding.FragmentCharactersDetailsBinding
import br.com.vaniala.starwars.ui.characters.viewmodel.CharactersDetailsViewModel
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
class CharactersDetailsFragment : Fragment() {

    private val arguments by navArgs<CharactersDetailsFragmentArgs>()
    private val character by lazy {
        arguments.character
    }
    private var _binding: FragmentCharactersDetailsBinding? = null
    private val binding: FragmentCharactersDetailsBinding get() = _binding!!

    private val viewModel: CharactersDetailsViewModel by viewModels()

    private val findNavController by lazy {
        findNavController()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCharactersDetailsBinding
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
        viewModel.savedStateHandle["isFavorite"] = character.isFavorite
        showCharacter()
        setFavoriteOnClickListener()
        observeFavoriteStatus()
        initButtonBack()
    }

    private fun initButtonBack() {
        binding.fragmentCharactersDetailsImgButtonBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setFavoriteOnClickListener() {
        binding.fragmentsCharacterDetailsImageFavorite.setOnClickListener {
            lifecycleScope.launch {
                viewModel.favorite(character)
            }
        }
    }
    private fun isLightMode(context: Context): Boolean {
        val darkModeFlag = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return darkModeFlag == Configuration.UI_MODE_NIGHT_NO
    }

    private fun showCharacter() {
        binding.apply {
            fragmentsCharactersDetailsImage.load(character.imagePeople)
            fragmentsCharactersDetailsHomeworldImage.load(character.imageHomeworld) {
                error(R.drawable.homeword)
            }
            fragmentsCharactersDetailsBirth.text = getString(R.string.characters_details_birth, character.birth_year)
            fragmentsCharactersDetailsGender.text = getString(R.string.characters_details_gender, character.gender)
            fragmentsCharactersDetailsHeight.text = getString(R.string.characters_details_height, character.height)
            fragmentsCharactersDetailsMass.text = getString(R.string.characters_details_mass, character.mass)
            fragmentsCharactersDetailsHair.text = getString(R.string.characters_details_hair, character.hair_color)
            fragmentsCharactersDetailsSkin.text = getString(R.string.characters_details_skin, character.skin_color)
            fragmentCharactersDetailsTextName.text = character.name
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
            binding.fragmentsCharacterDetailsImageFavorite.setImageResource(R.drawable.ic_action_favorite)
        } else {
            binding.fragmentsCharacterDetailsImageFavorite.setImageResource(R.drawable.ic_action_not_favorite)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        timber.log.Timber.d("onDestroyView")
    }
}
