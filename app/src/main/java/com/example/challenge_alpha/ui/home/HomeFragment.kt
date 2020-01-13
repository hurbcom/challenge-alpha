package com.example.challenge_alpha.ui.home


import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge_alpha.R
import com.example.challenge_alpha.api.Result
import com.example.challenge_alpha.di.Injectable
import javax.inject.Inject

/**
 * Fragmento de início do App. Provê uma barra de buscas [searchView],
 * além de duas recyclerviews horizontais para mostrar as últimas buscas
 * [lastSearchedRecycler] e últimos vistos [lastSeenRecycler].
 * O [LastSeenRecycler] mostra em ordem do último hotel/pacote visualizado,
 * enquanto que o [LastSearchRecycler] mostra em ordem
 * aleatória de acordo com as últimas buscas
 */
class HomeFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var searchView: SearchView
    private lateinit var searchViewRecycler: RecyclerView
    private lateinit var _context: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        _context = root.context

        homeViewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
        searchViewRecycler = root.findViewById(R.id.searchView_recycler)

        setHasOptionsMenu(true)

        /**
         * Classes responsáveis pelas recyclerviews dos últimos vistos e últimas buscas presentes no [HomeFragment]
         */
        lastSeenRecycler(root)
        lastSearchedRecycler(root)

        return root
    }



    /**
     * Classe responsável pela criação da searchview e encaminhamento das buscas para o [ResultsFragment]
     * ALém de solicitar sugestão de pesquisa ao servidor externo, e mostrar em uma recycler [searchViewRecycler]
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val navController = findNavController()

        val searchItem = menu.findItem(R.id.action_search)
        searchView = searchItem.actionView as SearchView
        searchView.isIconified = false
        searchView.maxWidth = Int.MAX_VALUE
        searchView.queryHint = getString(R.string.search_hint)
        searchView.setQuery(homeViewModel.queryString.value, false)
        searchView.clearFocus()


        val searchViewAdapter = SearchViewAdapter()
        val layoutLanguage = LinearLayoutManager(_context)
        searchViewRecycler.layoutManager = layoutLanguage
        searchViewRecycler.adapter = searchViewAdapter


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                if (query.isNotEmpty()) {
                    homeViewModel.search(query)
                    val action = HomeFragmentDirections.hotelsToResults(query)
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

            override fun onQueryTextChange(suggestion: String): Boolean {

                homeViewModel.search(suggestion)
                if (suggestion.count() > 2) {
                    homeViewModel.searchSuggestion(suggestion)
                }

                homeViewModel.getSuggetion.observe(this@HomeFragment, Observer { result ->

                    when (result.status) {
                        Result.Status.SUCCESS -> {
                            if (suggestion.count() > 2) {
                                searchViewAdapter.submitList(result.data?.suggestions)
                            } else {
                                searchViewAdapter.submitList(emptyList())
                            }

                        }
                        else -> {

                        }
                    }


                })

                return false
            }
        })

    }


    private fun lastSeenRecycler(view: View) {

        val lastSeen: RecyclerView = view.findViewById(R.id.lastSeen_display)
        val lastSeenTitle: TextView = view.findViewById(R.id.lastSeen_title)
        val lastSeenAdapter = LastSeenAdapter()

        val layoutHorizontal =
            GridLayoutManager(_context, 1, LinearLayoutManager.HORIZONTAL, false)


        lastSeen.layoutManager = layoutHorizontal

        lastSeen.adapter = lastSeenAdapter


        homeViewModel.getLastSeen.observe(this, Observer {
            if (it.isNotEmpty()) {
                lastSeen.visibility = View.VISIBLE
                lastSeenTitle.visibility = View.VISIBLE
                lastSeenAdapter.submitList(it)
            }

        })

    }

    private fun lastSearchedRecycler(view: View) {

        val lastSearch: RecyclerView = view.findViewById(R.id.lastSearch_display)
        val lastSearchTitle: TextView = view.findViewById(R.id.lastsearch_title)

        val layoutHorizontal =
            GridLayoutManager(_context, 1, LinearLayoutManager.HORIZONTAL, false)


        lastSearch.layoutManager = layoutHorizontal

        val lastSearchAdapter = LastSearchAdapter()

        lastSearch.adapter = lastSearchAdapter


        homeViewModel.getLastSearched.observe(this, Observer {
            if (it.isNotEmpty()) {
                lastSearchTitle.visibility = View.VISIBLE
                lastSearch.visibility = View.VISIBLE
                lastSearchAdapter.submitList(it)
            }
        })

    }


}