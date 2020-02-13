package br.com.rvvaranda.hu.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.rvvaranda.hu.Adapter.HotelsListAdapter
import br.com.rvvaranda.hu.Decoration.DividerDecoration
import br.com.rvvaranda.hu.Helper.RecycleViewPagination
import br.com.rvvaranda.hu.Model.Hotel
import br.com.rvvaranda.hu.R
import br.com.rvvaranda.hu.ViewModel.HotelsViewModel
import com.mikepenz.materialdrawer.Drawer
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {


    private lateinit var adapter: HotelsListAdapter

    private var hotels: MutableList<Hotel> = mutableListOf()
    private var isLoading: Boolean = false

    private var pageCount = 0
    private var currentPage = 1
    lateinit var result: Drawer


    private val viewModel = HotelsViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.title = "Hurb Challenger"

        prepareRecycleView()
        configAdapter()
        configRefreshList()
        loadHotels()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.getItemId()) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen) {
            result.closeDrawer()
        } else {
            super.onBackPressed()
        }
    }

    fun prepareRecycleView() {
        val divider = ContextCompat.getDrawable(this,
            R.drawable.divider
        )
        val decocation = DividerDecoration(divider!!)

        val layoutManager = LinearLayoutManager(this)
        recycleView.addItemDecoration(decocation)
        recycleView.layoutManager = layoutManager


        recycleView.addOnScrollListener(object : RecycleViewPagination(layoutManager) {

            override fun lastPage(): Boolean {
                return currentPage == pageCount
            }

            override fun loadPage(): Boolean {
                return isLoading
            }

            override fun loadMoreHotels() {
                currentPage += 1
                loadHotels()
            }
        })
    }

    fun configRefreshList() {

        swipeRefresh.setOnRefreshListener {
            hotels.clear()
            adapter.clearData()
            loadHotels()
        }
    }

    fun configAdapter() {
        adapter = HotelsListAdapter(hotels)
        recycleView.adapter = adapter
    }

    fun loadHotels() {
        try {
            showProgressBar(true)

            viewModel.loadAllHotels(currentPage)

                viewModel.lstHotels.observe(this, Observer {

                try {

                    hotels = viewModel.lstHotels.value!!
                    pageCount = viewModel.totalPages
                    adapter.addItens(hotels.sortedByDescending { it.stars })
                    adapter.notifyItemRangeChanged(hotels.size, hotels.size)
                    showProgressBar(false)
                } catch (ex: Exception) {
                    Log.e("MainActivity", ex.message)
                }
            })

        } catch (ex: Exception) {

        }
    }

    fun showProgressBar(show: Boolean) {

        if (show) {
            isLoading = true
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
            swipeRefresh.isRefreshing = false
            isLoading = false
        }
    }
}
