package com.example.challenge_alpha.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge_alpha.R
import com.example.challenge_alpha.databinding.FragmentFavoritesBinding
import com.example.challenge_alpha.di.Injectable
import javax.inject.Inject

/**
 * Display dos hotéis/pacotes favoritados pelo usuário em uma recycler vertical [favoritesRecycler]
 */
class FavoritesFragment : Fragment(), Injectable {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var favoritesViewModel: FavoritesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favoritesViewModel = ViewModelProvider(this, viewModelFactory).get(FavoritesViewModel::class.java)

        val binding: FragmentFavoritesBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_favorites, container, false)
        binding.lifecycleOwner = this
        binding.viewmodel = favoritesViewModel

        //     favoritesRecycler(root)

        return binding.root
    }

    private fun favoritesRecycler(view: View) {
        val recyclerFavorites: RecyclerView = view.findViewById(R.id.recyclerFavorite)
        recyclerFavorites.layoutManager = LinearLayoutManager(view.context)
        val adapter = FavoritesAdapter()
        recyclerFavorites.adapter = adapter

        favoritesViewModel.getFavorites.observe(this, Observer {
            adapter.submitList(it)
        })
    }


}