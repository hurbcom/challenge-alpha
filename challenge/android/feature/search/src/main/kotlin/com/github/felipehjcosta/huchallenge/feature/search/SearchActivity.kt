package com.github.felipehjcosta.huchallenge.feature.search

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.github.felipehjcosta.huchallenge.feature.search.di.setupDependencyInjection
import com.github.felipehjcosta.huchallenge.feature.search.viewmodel.SearchViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject

class SearchActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: SearchViewModel

    private lateinit var compositeDisposable: CompositeDisposable

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        setupDependencyInjection()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_activity)

        recyclerView = findViewById<RecyclerView>(R.id.hotels_recycler_view).apply {
            layoutManager = LinearLayoutManager(this@SearchActivity, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onStart() {
        super.onStart()
        compositeDisposable = CompositeDisposable()

        compositeDisposable += viewModel.items.subscribe {
            recyclerView.adapter = HotelsAdapter(it)
        }

        compositeDisposable += viewModel.loadItemsCommand.execute().subscribe()
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.dispose()
    }
}
