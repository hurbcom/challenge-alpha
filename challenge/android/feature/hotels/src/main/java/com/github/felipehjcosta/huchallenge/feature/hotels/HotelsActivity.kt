package com.github.felipehjcosta.huchallenge.feature.hotels

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class HotelsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hotels_activity)

        findViewById<RecyclerView>(R.id.hotels_recycler_view).apply {
            layoutManager = LinearLayoutManager(this@HotelsActivity, LinearLayoutManager.VERTICAL, false)
            adapter = HotelsAdapter()
        }
    }
}
