package com.barreto.android.presentation

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.barreto.android.R
import com.barreto.android.data.remote.RemoteError
import com.barreto.android.domain.base.Event
import com.barreto.android.domain.content.ContentItemError
import com.barreto.android.domain.content.model.ContentItem
import com.barreto.android.presentation.base.BaseNavigationActivity
import com.barreto.android.presentation.base.adapter.PagedAdapter
import com.barreto.android.presentation.base.view.PagedListLayout
import com.barreto.android.presentation.content.ContentViewModel
import com.barreto.android.presentation.content.ItemActivity
import com.barreto.android.presentation.content.adapter.ListAdapter
import com.google.android.material.snackbar.Snackbar
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : BaseNavigationActivity() {

    private var contentList: List<ContentItem> = emptyList()
    private var totalItems = 0

    private val viewModel by viewModel<ContentViewModel>()

    private val adapter = ListAdapter()
    protected val disposable = CompositeDisposable()

    private val toolbar by lazy { findViewById<Toolbar>(R.id.toolbar) }
    private val contentListView by lazy { findViewById<PagedListLayout>(R.id.content_list_view) }
    private var queryText = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buildToolbar()

        contentListView?.setOnRefreshListener {
            viewModel.getContentList(queryText)
        }
        contentListView?.setColorSchemeResources(R.color.colorPrimary)

        contentListView.adapter = this.adapter

        adapter.loadMoreListener = object : PagedAdapter.ILoadMoreListener {
            override fun onLoadMore() = viewModel.loadMore()
        }

        adapter.getNotifyItemClick().subscribe {
            val pos = it.first
            val item = it.second

            startActivity(
                ItemActivity.buildIntent(
                    this@MainActivity,
                    item,
                    item.name
                )
            )

        }.addTo(disposable)

        adapter.getNotifyFavoriteItemClick().subscribe {

            viewModel.addFavoriteList(it)
        }.addTo(disposable)

        initialize()
    }

    private fun initialize() {
        viewModel.hasNextPage.observe(this, Observer { adapter.loadEnable = (it == true) })
        viewModel.error.observe(this, Observer { showError(it) })
        viewModel.total.observe(this, Observer { totalItems = it ?: 0 })
        viewModel.contentList.observe(this, Observer { onContentsEvent(it) })
        viewModel.favorite.observe(this, Observer { onFavoriteEvent(it) })
    }

    private fun buildToolbar() {
        setSupportActionBar(toolbar)
        title = intent.getStringExtra(TOOLBAR_TITLE) ?: getString(R.string.app_name)

        startMenu(ITEM_1)
    }

    override fun onDestroy() {

        disposable.clear()
        super.onDestroy()
    }

    private fun onFavoriteEvent(event: Event<Boolean>?) {
        when (event) {
            is Event.Data -> {
                Snackbar.make(
                    contentListView,
                    "Item adicionado em sua lista de favoritos.",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
            is Event.Error -> {
                Timber.d(event.error)
                when (event.error.type) {
                    ContentItemError.CONTENT_ALREADY_EXISTS -> {
                        Snackbar.make(
                            contentListView,
                            "Item já foi adicionado em sua lista de favoritos.",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun onContentsEvent(event: Event<List<ContentItem>>?) {

        when (event) {
            is Event.Idle -> viewModel.getContentList()
            is Event.Data -> {
                contentList = event.data
                contentListView.isRefreshing = false
                if (event.data.isNullOrEmpty()) {
                    contentListView.showFeedbackStatus(
                        R.drawable.ic_search_black_24dp,
                        R.string.contents_not_found
                    )
                } else {
                    adapter.submitList(event.data)
                }
            }
            is Event.Loading -> contentListView.takeUnless { it.isRefreshing }?.isRefreshing = true
            is Event.Error -> showError(event)
        }
    }

    private fun showError(error: Event.Error?) {

        if (error == null) return

        contentListView.isRefreshing = false
        when (error.error.type) {
            RemoteError.UNKNOW_HOST -> if (adapter.itemCount <= 0) {
                contentListView.showFeedbackStatus(
                    feedbackTitle = R.string.error_internet_title,
                    feedbackMessage = R.string.error_internet_msg,
                    action = {
                        viewModel.getContentList()
                    }
                )
            } else {
                adapter.showLoadMoreError()
            }
            else -> contentListView.showFeedbackStatus(feedbackTitle = R.string.generic_error)
        }
        Timber.d(error.error)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_search, menu)

        val searchView = menu.findItem(R.id.menu_search).actionView as SearchView
        searchView.apply {
            setSearchableInfo(
                (getSystemService(Context.SEARCH_SERVICE) as SearchManager).getSearchableInfo(
                    componentName
                )
            )
            maxWidth = Integer.MAX_VALUE
            queryHint = getString(R.string.search)

            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    clearFocus()
                    queryText = query
                    viewModel.getContentList(query)
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    return false
                }
            })

            setOnCloseListener {
                queryText = ""
                viewModel.getContentList()
                false
            }
        }

        return super.onCreateOptionsMenu(menu)
    }

    companion object {

        fun buildIntent(
            context: Context,
            title: String
        ): Intent {

            return Intent(context, MainActivity::class.java)
                .apply {
                    putExtra(TOOLBAR_TITLE, title)
                }
        }
    }
}
