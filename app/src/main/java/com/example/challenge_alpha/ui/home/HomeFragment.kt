package com.example.challenge_alpha.ui.home


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
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


class HomeFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var searchView: SearchView
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

        setHasOptionsMenu(true)

        lastSeen(root)
        lastSearched(root)

        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val navController = findNavController()

        val searchItem = menu.findItem(R.id.action_search)
        searchView = searchItem.actionView as SearchView
        searchView.isIconified = false
        searchView.isIconifiedByDefault = false
        searchView.maxWidth = Int.MAX_VALUE
        searchView.queryHint = getString(R.string.search_hint)
        searchView.clearFocus()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                if (query!!.isNotEmpty()) {
                    homeViewModel.search(query.toString())
                    Log.d(TAG, query.toString())
                    val action = HomeFragmentDirections.hotelsToResults(query.toString())
                    navController.navigate(action)
                } else {
                    Toast.makeText(
                        _context,
                        getString(R.string.search_error),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return false
            }
        })

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