package br.com.rvvaranda.hu.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import br.com.rvvaranda.hu.Adapter.HotelsListAdapter
import br.com.rvvaranda.hu.Decoration.DividerDecoration
import br.com.rvvaranda.hu.Helper.RecycleViewPagination
import br.com.rvvaranda.hu.Model.Hotel
import br.com.rvvaranda.hu.R
import br.com.rvvaranda.hu.Repository.HotelsRepository
import br.com.rvvaranda.hu.ViewModel.Factory.HotelsViewModelFactory
import br.com.rvvaranda.hu.ViewModel.HotelsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: HotelsListAdapter
    private lateinit var refreshList: SwipeRefreshLayout


    private var hotels: MutableList<Hotel> = mutableListOf()
    private var isLoading: Boolean = false

    private var pageCount = 0
    private var currentPage = 1



    private val viewModel by lazy {

        val repository = HotelsRepository()
        val factory = HotelsViewModelFactory(repository, currentPage)
        val provider = ViewModelProviders.of(this, factory)
        provider.get(HotelsViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        progressBar = findViewById(R.id.progressBar)
        prepareRecycleView()
        configAdapter()
        configRefreshList()
        loadHotels()
    }

    fun prepareRecycleView() {
        recyclerView = findViewById(R.id.recycleView)
        val divider = ContextCompat.getDrawable(this,
            R.drawable.divider
        )
        val decocation = DividerDecoration(divider!!)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(decocation)
        recyclerView.layoutManager = layoutManager


        recyclerView.addOnScrollListener(object : RecycleViewPagination(layoutManager) {

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
        refreshList = findViewById(R.id.swipeRefresh)


        refreshList.setOnRefreshListener {
            hotels.clear()
            adapter.clearData()
            loadHotels()
        }
    }

    fun configAdapter() {
        adapter = HotelsListAdapter(hotels)
        recyclerView.adapter = adapter
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
