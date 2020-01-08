package com.example.challenge_alpha.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge_alpha.R
import com.example.challenge_alpha.di.Injectable
import javax.inject.Inject

class HomeFragment : Fragment(), View.OnClickListener, Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var searchInput: EditText
    private lateinit var _context: Context
    private val TAG = "HurbCall"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        _context = root.context

        homeViewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
        searchInput = root.findViewById(R.id.search_input)
        searchInput.setText(homeViewModel.queryString.value)

        setListener(root)

        lastSeen(root)
        lastSearched(root)

        return root
    }

    private fun setListener(view: View) {
        val searchButton = view.findViewById<Button>(R.id.search_button)
        searchButton.setOnClickListener(this)

    }

    override fun onClick(view: View) {

        val navController = findNavController()

        when (view.id) {
            R.id.search_button -> {
                searchInput.text.trim().let {
                    if (it.isNotEmpty()) {
                        homeViewModel.search(it.toString())
                        val action = HomeFragmentDirections.hotelsToResults(it.toString())
                        navController.navigate(action)
                    } else {
                        Toast.makeText(
                            _context,
                            getString(R.string.search_error),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            }

        }

    }

    private fun lastSeen(view: View) {

        val lastSeen: RecyclerView = view.findViewById(R.id.lastSeen_display)
        val lastSeenTitle: TextView = view.findViewById(R.id.lastSeen_title)
        val lastSeenAdapter = LastSeenAdapter()

        val layoutHorizontalLanguage =
            GridLayoutManager(_context, 1, LinearLayoutManager.HORIZONTAL, false)


        lastSeen.layoutManager = layoutHorizontalLanguage

        lastSeen.adapter = lastSeenAdapter


        homeViewModel.getLastSeen.observe(this, Observer {
            Log.d(TAG + "seen", "${it.firstOrNull()}")
            if (it.isNotEmpty()) {
                lastSeen.visibility = View.VISIBLE
                lastSeenTitle.visibility = View.VISIBLE
                lastSeenAdapter.submitList(it)
            }

        })

    }

    private fun lastSearched(view: View) {

        val lastSearch: RecyclerView = view.findViewById(R.id.lastSearch_display)
        val lastSearchTitle: TextView = view.findViewById(R.id.lastsearch_title)

        val layoutHorizontalLanguage =
            GridLayoutManager(_context, 1, LinearLayoutManager.HORIZONTAL, false)


        lastSearch.layoutManager = layoutHorizontalLanguage

        val lastSearchAdapter = LastSearchAdapter()

        lastSearch.adapter = lastSearchAdapter


        homeViewModel.getLastSearched.observe(this, Observer {
            Log.d(TAG + "searched", "$it")
            if (it.isNotEmpty()) {
                lastSearchTitle.visibility = View.VISIBLE
                lastSearch.visibility = View.VISIBLE
                lastSearchAdapter.submitList(it)
            }
        })

    }


}