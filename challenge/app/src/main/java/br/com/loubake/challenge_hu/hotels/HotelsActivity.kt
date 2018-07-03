package br.com.loubake.challenge_hu.hotels

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import br.com.loubake.challenge_hu.R

class HotelsActivity : AppCompatActivity(), HotelsContract.View {

    lateinit var toolbar: Toolbar
    lateinit var mRecyclerHotels: RecyclerView
    lateinit var mProgress: ProgressBar
    lateinit var mErrorTxt: TextView
    lateinit var mPresenter: HotelsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotels)

        toolbar = findViewById<Toolbar>(R.id.toolbar)
        mRecyclerHotels = findViewById<RecyclerView>(R.id.recycler_hotels)
        mProgress = findViewById<ProgressBar>(R.id.progress_hotels)
        mErrorTxt = findViewById<TextView>(R.id.error_hotels)

        toolbar.setTitle(R.string.app_name)
        setSupportActionBar(toolbar)

        mPresenter = HotelsPresenter(this, this)
        mPresenter.loadHotels()
    }

    override fun showLoading() {
        mProgress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        mProgress.visibility = View.GONE
    }

    override fun showHotelsList() {
        mRecyclerHotels.visibility = View.VISIBLE
    }

    override fun showErrorLayout() {
        mErrorTxt.visibility = View.VISIBLE
    }

    override fun setHotelsList(results: List<Any>) {
        mRecyclerHotels.layoutManager = LinearLayoutManager(this).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
        var adapter = HotelsAdapter(this, results)
        mRecyclerHotels.adapter = adapter
    }
}