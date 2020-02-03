package br.com.flyingdutchman.challenge_alpha

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val adapter by lazy {
        ResultAdapter()
    }

    private val viewModel: SearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycle.addObserver(viewModel)

        viewModel.loading.observe(this, Observer {
            if (it) {
                activity_results_loading.show()
            } else {
                activity_results_loading.hide()
            }

        })

        viewModel.success.observe(this, Observer {
            handleSuccess(it)
        })

        viewModel.error.observe(this, Observer {
            handleError(it)
        })

        setupRecyclerView()
    }


    private fun setupRecyclerView() {
        activity_results_recycler_view
            .apply {
                layoutManager =
                    GridLayoutManager(this@MainActivity, 2, GridLayoutManager.VERTICAL, false)
                addItemDecoration(
                    SpacesItemDecoration(
                        resources.getDimension(R.dimen.card_margin).toInt()
                    )
                )
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
            activity_results_loading.hide()
            activity_search_results_content_root
                .snackBar(getString(R.string.generic_error)) {
                    viewModel.loadResults()
                }
                .show()
        }
    }

}
