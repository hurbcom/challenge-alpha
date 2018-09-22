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

    /**
     * Separa os hoteis dos pacotes em duas listas
     */

    private var hotels = ArrayList<Result>()
    private var packages = ArrayList<Result>()

    /**
     * Inicia a API
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

    /**
     * Requisição de dados dos results filtrando quais são hoteis e quais são pacotes
     */
    private fun fetchData(){
        disposable = iMyAPI.fetchResult("Rio%20de%20Janeiro")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {result ->
                    var results = result.getResults()!!
                    results.forEach {
                        if (it.getIsHotel()){
                            hotels.add(it)
                        }
                        else{
                            packages.add(it)
                        }
                    }
                    val sortedResults = hotels.sortedWith(compareBy({it.getStars()}))
                    displayData(sortedResults)
                },
                {error -> Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()}
            )
    }

    /**
     * Exibi os dados
     */
    private fun displayData(result: List<Result>?) {
        val adapter = ResultAdapter(this,result!!)
        recyclerView.adapter = adapter
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}
