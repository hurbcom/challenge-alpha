package com.example.challenge_alpha.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.challenge_alpha.R
import com.example.challenge_alpha.databinding.FragmentFavoritesBinding
import com.example.challenge_alpha.di.Injectable
import com.example.challenge_alpha.testing.OpenForTesting
import javax.inject.Inject

/**
 * Display dos hotéis/pacotes favoritados pelo usuário em uma recycler vertical [favoritesRecycler]
 */
@OpenForTesting
class FavoritesFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var favoritesViewModel: FavoritesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favoritesViewModel =
            ViewModelProvider(this, viewModelFactory).get(FavoritesViewModel::class.java)

        val binding: FragmentFavoritesBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_favorites, container, false)
        binding.lifecycleOwner = this
        binding.favorites = favoritesViewModel.getFavorites()


        return binding.root
    }
}