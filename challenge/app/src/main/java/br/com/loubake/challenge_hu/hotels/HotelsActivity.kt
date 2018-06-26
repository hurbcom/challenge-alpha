package br.com.loubake.challenge_hu.hotels

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.widget.ProgressBar
import android.widget.Toast
import br.com.loubake.challenge_hu.R
import br.com.loubake.challenge_hu.data.HotelResults

class HotelsActivity : AppCompatActivity(), HotelsContract.View {

    lateinit var toolbar: Toolbar
    lateinit var mRecyclerHotels: RecyclerView
    lateinit var mProgress: ProgressBar
    lateinit var mPresenter: HotelsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotels)

        toolbar = findViewById<Toolbar>(R.id.toolbar)
        mRecyclerHotels = findViewById<RecyclerView>(R.id.recycler_hotels)
        mProgress = findViewById<ProgressBar>(R.id.progress_hotels)

        toolbar.setTitle(R.string.app_name)
        setSupportActionBar(toolbar)

        mPresenter = HotelsPresenter(this, this)
        mPresenter.loadHotels()
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showHotelsList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showErrorLayout() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setHotelsList(results: List<HotelResults.Hotel>?) {
        Toast.makeText(this,
            "Sucesso: " + results?.get(0)?.name,
            Toast.LENGTH_LONG).show()
    }
}