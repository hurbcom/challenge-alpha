package br.com.rvvaranda.hu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import br.com.rvvaranda.hu.Adapter.HotelsListAdapter
import br.com.rvvaranda.hu.Decoration.DividerDecoration
import br.com.rvvaranda.hu.Helper.CallbackAppHu
import br.com.rvvaranda.hu.Helper.RecycleViewPagination
import br.com.rvvaranda.hu.Model.Hotel
import br.com.rvvaranda.hu.Model.Meta
import br.com.rvvaranda.hu.Repository.SearchRepository
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {


    lateinit var recyclerView: RecyclerView
    lateinit var progressBar: ProgressBar
    lateinit var etLocalizar: EditText
    lateinit var adapter: HotelsListAdapter
    lateinit var resultData: Meta
    lateinit var refreshList: SwipeRefreshLayout

    var Hotels = ArrayList<Hotel>()
    var isLoading: Boolean = false

    var pageCount = 1
    var currentPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerView = findViewById(R.id.recycleView)
        progressBar = findViewById(R.id.progressBar)

        refreshList = findViewById(R.id.swipeRefresh)


        val divider = ContextCompat.getDrawable(this, R.drawable.divider)
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

        refreshList.setOnRefreshListener {
            Hotels.clear()
            adapter.clearData()
            loadHotels()
        }

        adapter = HotelsListAdapter(Hotels)
        recyclerView.adapter = adapter

        loadHotels()
    }

    fun loadHotels() {
        try {
            showProgressBar(true)

            SearchRepository().LoadHotels(currentPage, object : CallbackAppHu<String> {
                override fun onComplete(result: String) {
                    if (!result.equals("")) {
                        resultData = Gson().fromJson(result, Meta::class.java)

                        runOnUiThread {
                            try {
                                val size = Hotels.size
                                Hotels.addAll(resultData.hotels.sortedByDescending { it.stars })
                                pageCount = resultData.pagination.count
                                adapter.addItens(Hotels)
                                adapter.notifyItemRangeChanged(size, Hotels.size)
                                showProgressBar(false)
                            } catch (ex: Exception) {
                                Log.e("MainActivity", ex.message)
                            }
                        }
                    } else {
                        runOnUiThread {
                            showProgressBar(false)

                            AlertDialog.Builder(this@MainActivity)
                                .setTitle("Desafio HU")
                                .setMessage(R.string.error_message)
                                .setPositiveButton(R.string.ok_title, null)
                                .create()
                                .show()
                        }
                    }
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
