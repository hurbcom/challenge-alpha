package com.barreto.android.presentation.content

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.barreto.android.R
import com.barreto.android.domain.base.Event
import com.barreto.android.domain.content.model.ContentItem
import com.barreto.android.presentation.base.BaseNavigationActivity
import com.barreto.android.presentation.base.view.PagedListLayout
import com.barreto.android.presentation.content.adapter.ListAdapter
import com.google.android.material.snackbar.Snackbar
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class FavoritesActivity : BaseNavigationActivity() {

    private var undo: Boolean = false
    private var contentList: List<ContentItem> = emptyList()
    private var contentItem: ContentItem = ContentItem()
    private var totalItems = 0

    private val viewModel by viewModel<ContentViewModel>()

    private val adapter = ListAdapter()
    protected val disposable = CompositeDisposable()

    private val toolbar by lazy { findViewById<Toolbar>(R.id.toolbar) }
    private val contentListView by lazy { findViewById<PagedListLayout>(R.id.content_list_view) }
    private val listBreadcrumbText by lazy { findViewById<TextView>(R.id.listBreadcrumbText) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buildToolbar()

        contentListView?.setOnRefreshListener {
//            viewModel.getFavoriteList()
            contentListView.isRefreshing = false
        }
        adapter.loadEnable = false

        contentListView.adapter = this.adapter

        adapter.getNotifyItemClick().subscribe {
            val pos = it.first
            val item = it.second

            startActivity(
                ItemActivity.buildIntent(
                    this@FavoritesActivity,
                    item,
                    item.name
                )
            )

        }.addTo(disposable)

        adapter.getNotifyFavoriteItemClick().subscribe {

            contentItem = it
            undo = false
            viewModel.deleteFavoriteList(it)
        }.addTo(disposable)

        initialize()
    }

    private fun initialize() {
        viewModel.total.observe(this, Observer { totalItems = it ?: 0 })
        viewModel.favoriteList.observe(this, Observer { onContentsEvent(it) })
        viewModel.favorite.observe(this, Observer { onFavoriteEvent(it) })
    }

    private fun buildToolbar() {
        startMenu(ITEM_2)
        title = intent.getStringExtra(TOOLBAR_TITLE) ?: getString(R.string.app_name)
    }

    override fun onDestroy() {

        disposable.clear()
        super.onDestroy()
    }

    private fun onFavoriteEvent(event: Event<Boolean>?) {
        when (event) {
            is Event.Data -> {

                viewModel.getFavoriteList()
                if (!undo) {
                    Snackbar.make(
                        contentListView,
                        "Item Removido em sua lista de favoritos.",
                        Snackbar.LENGTH_LONG
                    )
                        .setAction("DESFAZER") {
                            undo = true
                            viewModel.addFavoriteList(contentItem)
                            Snackbar.make(
                                contentListView,
                                "Item restaurado!",
                                Snackbar.LENGTH_SHORT
                            ).show()
                        }
                        .show()
                }
            }
            is Event.Error -> Timber.d(event.error)
        }
    }

    private fun onContentsEvent(event: Event<List<ContentItem>>?) {

        listBreadcrumbText.visibility = View.GONE
        when (event) {
            is Event.Idle -> viewModel.getFavoriteList()
            is Event.Data -> {
                listBreadcrumbText.visibility = View.VISIBLE
                listBreadcrumbText.text = String.format(getString(R.string.list_breadcrumb), totalItems)
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
            is Event.Error -> {
                Timber.d(event.error)
            }
        }
    }

    companion object {

        fun buildIntent(
            context: Context,
            title: String
        ): Intent {

            return Intent(context, FavoritesActivity::class.java)
                .apply {
                    putExtra(TOOLBAR_TITLE, title)
                }
        }
    }
}
