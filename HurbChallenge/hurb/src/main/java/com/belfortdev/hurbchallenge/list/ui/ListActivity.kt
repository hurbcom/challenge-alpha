package com.belfortdev.hurbchallenge.list.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.belfortdev.hurbchallenge.R
import com.belfortdev.hurbchallenge.core.BaseActivity
import com.belfortdev.hurbchallenge.core.model.SearchDomain
import com.belfortdev.hurbchallenge.core.network.Outcome
import com.belfortdev.hurbchallenge.list.injection.HotelDH
import com.belfortdev.hurbchallenge.list.viewmodel.ListViewModel
import com.belfortdev.hurbchallenge.list.viewmodel.ListViewModelFactory
import kotlinx.android.synthetic.main.activity_list.*
import java.io.IOException
import javax.inject.Inject

class ListActivity : BaseActivity() {

    companion object {
        private const val TAG = "ListActivity"
    }

    @Inject
    lateinit var viewModelFactory: ListViewModelFactory

    @Inject
    lateinit var adapter: ListAdapter

    private val component by lazy { HotelDH.listComponent() }
    private val viewModel: ListViewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(ListViewModel::class.java) }
    private val context: Context by lazy { this }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_list)
        component.inject(this)

        hotelRecyclerView.layoutManager = LinearLayoutManager(this)
        hotelRecyclerView.adapter = adapter
        swipeToRefreshHotels.setOnRefreshListener { viewModel.refreshHotels() }

        viewModel.getHotels()
        initDataListener()
    }

    private fun initDataListener() {
        viewModel.hotelsOutcome.observe(
            this,
            Observer<Outcome<List<SearchDomain.Hotel>>> { outcome ->
                Log.d(TAG, "initDataListener: " + outcome.toString())
                when (outcome) {

                    is Outcome.Progress -> swipeToRefreshHotels.isRefreshing = outcome.loading

                    is Outcome.Success -> {
                        Log.d(TAG, "initDataListener: Successfully loaded data")
                        adapter.setData(outcome.data)
                    }

                    is Outcome.Failure -> {
                        if (outcome.e is IOException)
                            Toast.makeText(
                                context,
                                "No connection to the network",
                                Toast.LENGTH_LONG
                            ).show()
                        else
                            Toast.makeText(
                                context,
                                "Failed to load hotels, please try again later",
                                Toast.LENGTH_LONG
                            ).show()
                    }

                }
            }
        )
    }
}
