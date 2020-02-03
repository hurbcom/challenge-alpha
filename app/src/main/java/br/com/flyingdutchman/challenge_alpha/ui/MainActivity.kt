package br.com.flyingdutchman.challenge_alpha.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import br.com.flyingdutchman.challenge_alpha.R
import br.com.flyingdutchman.challenge_alpha.commons.*
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {


    private val gridLayoutManager by lazy {
        GridLayoutManager(this@MainActivity, 2, GridLayoutManager.VERTICAL, false)
    }

    private val adapter by lazy {
        SearchResultAdapter(
            {

            },
            gridLayoutManager
        )
    }

    private val viewModel: SearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)

        setContentView(R.layout.activity_main)

        lifecycle.addObserver(viewModel)

        viewModel.success.observe(this, Observer {
            handleSuccess(it)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_list, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_change_layout -> {
                if (gridLayoutManager.spanCount == 1) {
                    gridLayoutManager.spanCount = 2
                    item.title = "list"
                } else {
                    gridLayoutManager.spanCount = 1
                    item.title = "grid"
                }
                adapter.notifyItemRangeChanged(0, adapter.itemCount)
            }
        }

        return super.onOptionsItemSelected(item)
    }


    private fun setupRecyclerView() {
        activity_results_recycler_view
            .apply {
                layoutManager = gridLayoutManager
                addItemDecoration(
                    SpacesItemDecoration(
                        resources.getDimension(R.dimen.card_margin).toInt()
                    )
                )
                setHasFixedSize(true)
                adapter = this@MainActivity.adapter
            }
    }

    private fun handleSuccess(it: SingleLiveEvent<List<Result>>?) {
        it?.getContentIfNotHandled()?.let {
            activity_results_recycler_view.show()
            adapter.updateItems(it)
        }
    }

    private fun handleError(it: SingleLiveEvent<Throwable>?) {
        it?.getContentIfNotHandled()?.let {
            activity_results_recycler_view.hide()
            activity_search_results_content_root
                .snackBar(getString(R.string.generic_error)) {
                    viewModel.loadResults()
                }
                .show()
        }
    }

}
