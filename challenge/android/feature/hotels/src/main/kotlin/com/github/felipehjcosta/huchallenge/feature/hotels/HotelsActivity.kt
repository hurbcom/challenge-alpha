package com.github.felipehjcosta.huchallenge.feature.hotels

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.github.felipehjcosta.huchallenge.feature.hotels.di.setupDependencyInjection
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject

class HotelsActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: HotelsViewModel

    private lateinit var compositeDisposable: CompositeDisposable

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        setupDependencyInjection()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hotels_activity)

        recyclerView = findViewById<RecyclerView>(R.id.hotels_recycler_view).apply {
            layoutManager = LinearLayoutManager(this@HotelsActivity, LinearLayoutManager.VERTICAL, false)
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
