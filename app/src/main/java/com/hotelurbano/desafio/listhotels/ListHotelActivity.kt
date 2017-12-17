package com.hotelurbano.desafio.listhotels

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.hotelurbano.desafio.R
import com.hotelurbano.desafio.base.BaseActivity
import com.hotelurbano.desafio.detailshotels.DetailsHotelActivity
import com.hotelurbano.desafio.listhotels.di.DaggerListHotelActivityComponent
import com.hotelurbano.desafio.listhotels.di.ListHotelActivityModule
import com.hotelurbano.desafio.listhotels.filter.Criteria
import com.hotelurbano.desafio.listhotels.filter.CriteriaPackage
import com.hotelurbano.desafio.listhotels.filter.CriteriaThree
import com.hotelurbano.desafio.listhotels.model.HotelItem
import com.hotelurbano.desafio.listhotels.presenter.ListHotelPresenter
import com.hotelurbano.desafio.listhotels.view.ListHotelView
import com.hotelurbano.desafio.listhotels.viewholder.HotelSection
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_list_hotel.*
import org.greenrobot.eventbus.Subscribe
import org.jetbrains.anko.toast
import java.io.Serializable
import javax.inject.Inject

class ListHotelActivity : BaseActivity(), ListHotelView {

    @Inject
    lateinit var presenter: ListHotelPresenter

    private lateinit var listAdapter: SectionedRecyclerViewAdapter

    private val city = "Rio de Janeiro"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_hotel)

        presenter.getHotels(city)
    }

    override fun onActivityInject() {
        DaggerListHotelActivityComponent.builder().appComponent(getAppcomponent())
            .listHotelActivityModule(ListHotelActivityModule())
            .build()
            .inject(this)

        presenter.attachView(this)
    }

    override fun onResponse(list: List<HotelItem>?) {
        /*Só implementei 3 estrelas e pacotes, apesar desse trecho estar seguindo o principio
        Open/Closed do Solid. Se necessário estender, só precisar criar outros 'Criterias' como por
        exemplo 1, 2, 4 ou 5 estrelas.*/
        val map = LinkedHashMap<String, Criteria>()
        map.put("3 estrelas", CriteriaThree())
        map.put("Pacotes", CriteriaPackage())

        listAdapter = SectionedRecyclerViewAdapter()

        for (entry in map) {
            listAdapter.addSection(HotelSection(entry.key, entry.value.startsCriteria(list)))
        }

        with(hotelList) {
            layoutManager = android.support.v7.widget.LinearLayoutManager(this@ListHotelActivity)
            adapter = listAdapter
        }
    }

    override fun showProgress() {
        hotelList.visibility = View.GONE
        hotelLogo.visibility = View.GONE
        hotelProgress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        hotelProgress.visibility = View.GONE
        hotelList.visibility = View.VISIBLE
    }

    override fun onError() {
        super.onError()
        hotelLogo.visibility = View.VISIBLE
    }

    override fun noResult() {
        hotelLogo.visibility = View.VISIBLE
        toast("We couldn't find any repository.")
    }

    @Subscribe
    fun onRowClicked(item: HotelItem) {
        val intent = Intent(this, DetailsHotelActivity::class.java)
        intent.putExtra("Hotel", item as Serializable)
        startActivity(intent)
    }
}
