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
import br.com.flyingdutchman.challenge_alpha.ui.search.model.RailsSearchResults
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: SearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)

        setContentView(R.layout.activity_main)

        lifecycle.addObserver(viewModel)

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
            activity_results_recycler_view.adapter =
                SearchResultRailsAdapter(
                    list
                )
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
