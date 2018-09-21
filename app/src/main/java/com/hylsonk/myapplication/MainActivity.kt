package com.hylsonk.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.hylsonk.myapplication.Adapter.ResultAdapter
import com.hylsonk.myapplication.Model.Result
import com.hylsonk.myapplication.Retrofit.IMyAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var disposable:Disposable ? = null


    /*
        Inicia a API
     */
    private val iMyAPI by lazy {
        IMyAPI.create()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchData()
    }

    private fun fetchData(){
        disposable = iMyAPI.fetchResult("Rio%20de%20Janeiro")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {result ->
                    var results: ArrayList<Result> = result.getResults()!!
                    results = results.sortBy{ it.getStars() }
                    displayData(results)
                },
                {error -> Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()}
            )
    }

    private fun displayData(result: List<Result>?) {
        val adapter = ResultAdapter(this,result!!)
        recyclerView.adapter = adapter
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}
