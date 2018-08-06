package com.github.felipehjcosta.huchallenge.feature.search

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.support.v4.widget.ContentLoadingProgressBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.github.felipehjcosta.huchallenge.base.imageloader.ImageLoader
import com.github.felipehjcosta.huchallenge.feature.search.di.setupDependencyInjection
import com.github.felipehjcosta.huchallenge.feature.search.viewmodel.SearchViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject

class SearchActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: SearchViewModel

    @Inject
    lateinit var imageLoader: ImageLoader

    private lateinit var compositeDisposable: CompositeDisposable

    private lateinit var recyclerView: RecyclerView

    private lateinit var loadingView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        setupDependencyInjection()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_activity)

        loadingView = findViewById(R.id.loading_view)

        recyclerView = findViewById<RecyclerView>(R.id.hotels_recycler_view).apply {
            layoutManager = LinearLayoutManager(this@SearchActivity, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onStart() {
        super.onStart()
        compositeDisposable = CompositeDisposable()

        compositeDisposable += viewModel.showLoading
                .map { if (it) recyclerView to loadingView else loadingView to recyclerView }
                .subscribe { crossFade(it.first, it.second) }

        compositeDisposable += viewModel.items.subscribe {
            recyclerView.adapter = HotelsAdapter(it, imageLoader)
        }

        compositeDisposable += viewModel.loadItemsCommand.execute().subscribe()
    }

    private fun crossFade(fromView: View, toView: View) {

        fromView.visibility = View.VISIBLE

        // Set the content view to 0% opacity but visible, so that it is visible
        // (but fully transparent) during the animation.
        toView.alpha = 0.0f
        toView.visibility = View.VISIBLE

        // Animate the content view to 100% opacity, and clear any animation
        // listener set on the view.

        val shortAnimationDuration = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()

        toView.animate()
                .alpha(1f)
                .setDuration(shortAnimationDuration)
                .setListener(null)

        // Animate the loading view to 0% opacity. After the animation ends,
        // set its visibility to GONE as an optimization step (it won't
        // participate in layout passes, etc.)
        fromView.animate()
                .alpha(0f)
                .setDuration(shortAnimationDuration)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        fromView.visibility = View.GONE
                    }
                })
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.dispose()
    }
}
