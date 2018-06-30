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
import br.com.loubake.challenge_hu.data.HotelResults

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

    override fun setHotelsList(results: List<HotelResults.Hotel>?) {
        if (results == null || results.isEmpty()) {
            showErrorLayout()
            return
        }

        var hotels = ArrayList<HotelResults.Hotel>(results)

        hotels.sortByDescending {
            it.stars
        }
        var groupedList = ArrayList<Any>(hotels)

        val starsCount : Int? = (groupedList[0] as HotelResults.Hotel)?.stars
        if (starsCount != null) {
            groupedList.add(0, getString(R.string.group_stars, starsCount))
            for (i in 1..groupedList.size) {
                if (groupedList[i] !is String) {
                        val currentItem = groupedList[i] as HotelResults.Hotel
                        val nextItem = groupedList[i+1] as HotelResults.Hotel
                    if (i + 1 < groupedList.size) {
                        if (nextItem.isPackage && currentItem.isHotel) {
                            groupedList.add(i+1, getString(R.string.group_package))
                            break
                        }
                        if (nextItem.stars != currentItem.stars) {
                            groupedList.add(i+1, getString(R.string.group_stars, nextItem.stars))
                        }
                    }
                }
            }
        }

        mRecyclerHotels.layoutManager = LinearLayoutManager(this).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
        var adapter = HotelsAdapter(this, groupedList)
        mRecyclerHotels.adapter = adapter
    }
}