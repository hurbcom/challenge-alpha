package com.example.challenge_alpha.ui.favorites

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge_alpha.ChallengeApplication
import com.example.challenge_alpha.R
import com.example.challenge_alpha.di.Injectable
import com.example.challenge_alpha.repository.FavoritesRepository
import com.example.challenge_alpha.ui.results.ResultsAdapter
import javax.inject.Inject

class FavoritesFragment : Fragment(), Injectable {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var favoritesViewModel: FavoritesViewModel
    private lateinit var recyclerFavorites: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_favorites, container, false)
        favoritesViewModel = ViewModelProvider(this, viewModelFactory).get(FavoritesViewModel::class.java)

        recyclerFavorites = root.findViewById(R.id.recyclerFavorite)
        recyclerFavorites.layoutManager = LinearLayoutManager(root.context)
        val adapter = FavoritesAdapter()
        recyclerFavorites.adapter = adapter

        favoritesViewModel.getFavorites.observe(this, Observer {
            adapter.submitList(it)
        })

        return root
    }

}