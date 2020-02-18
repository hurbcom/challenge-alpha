package viniciusapp.com.br.hurbtest.ui.activities

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import viniciusapp.com.br.hurbtest.R
import viniciusapp.com.br.hurbtest.models.ResultsModel
import viniciusapp.com.br.hurbtest.ui.adapters.HotelAdapter

class MainActivity : BaseActivity() {
    private lateinit var progressBar: ProgressBar
    private lateinit var viewModel: MainViewModel
    var searchCity: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar = findViewById(R.id.progressBar)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.hotelsLiveData.observe(this,
            Observer<List<ResultsModel>> { hotels ->
                initComponents(hotels!!)
                loadingList(false, progressBar)
            })

        viewModel.errorLiveData.observe(this,
            Observer<String> {
                    errorMessage ->
                onError(errorMessage)
            }
        )

        imbSearch.setOnClickListener {
            searchCity = edtSearch.text.toString()
            callHotels()
        }
    }

    private fun initComponents(response: List<ResultsModel>) {
        val recycler = findViewById<RecyclerView>(R.id.rcvHotels)
        val sortedList = response.sortedByDescending { item -> item.stars}

        recycler.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        recycler.adapter = HotelAdapter(applicationContext, sortedList)
    }

    private fun callHotels() {
        loadingList(true, progressBar)
        viewModel.executeRequest(searchCity)
    }

    private fun onError(message: String?) {
        loadingList(false, progressBar)
        showError(message!!)
    }

    private fun loadingList(loading: Boolean, progressBar: ProgressBar) {
        progressBar.visibility = if (loading) View.VISIBLE else View.GONE
    }
}
