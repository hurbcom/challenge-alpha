package br.com.flyingdutchman.challenge_alpha.ui.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.flyingdutchman.challenge_alpha.R
import br.com.flyingdutchman.challenge_alpha.commons.SingleLiveEvent
import br.com.flyingdutchman.challenge_alpha.commons.hide
import br.com.flyingdutchman.challenge_alpha.commons.show
import br.com.flyingdutchman.challenge_alpha.commons.snackBar
import br.com.flyingdutchman.challenge_alpha.ui.detail.DetailsActivity.Companion.SEARCH_RESULT
import br.com.flyingdutchman.challenge_alpha.ui.search.model.RailsSearchResults
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: SearchViewModel by viewModel()

    private lateinit var adapter: SearchResultRailsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)

        setContentView(R.layout.activity_main)

        savedInstanceState?.let {
            if (it.containsKey(SEARCH_RESULT)) {
                adapter =
                    SearchResultRailsAdapter(savedInstanceState.getParcelableArrayList(SEARCH_RESULT))

                activity_results_recycler_view.adapter = adapter
            }
        } ?: run {
            lifecycle.addObserver(viewModel)
        }


        viewModel.successRails.observe(this, Observer {
            handleRailsSuccess(it)
        })

        viewModel.error.observe(this, Observer {
            handleError(it)
        })

        viewModel.loading.observe(this, Observer {
            if (it) {
                activity_results_loading.show()
            } else {
                activity_results_loading.hide()
            }

        })

        setupRecyclerView()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        if (::adapter.isInitialized && adapter.items.isNotEmpty()) outState.putParcelableArrayList(
            SEARCH_RESULT,
            adapter.items as ArrayList
        )

        super.onSaveInstanceState(outState)
    }


    private fun setupRecyclerView() {
        activity_results_recycler_view
            .apply {

                layoutManager =
                    LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
                setHasFixedSize(true)
            }
    }

    private fun handleRailsSuccess(it: SingleLiveEvent<List<RailsSearchResults>>?) {
        it?.getContentIfNotHandled()?.let { list ->
            activity_results_recycler_view.show()
            adapter = SearchResultRailsAdapter(list)
            activity_results_recycler_view.adapter = adapter

        }
    }


    private fun handleError(it: SingleLiveEvent<Throwable>?) {
        it?.getContentIfNotHandled()?.let {
            activity_results_recycler_view.hide()
            activity_search_results_content_root
                .snackBar(getString(R.string.generic_error)) {
                    viewModel.loadAllTypesResults()
                }
                .show()
        }
    }

}
