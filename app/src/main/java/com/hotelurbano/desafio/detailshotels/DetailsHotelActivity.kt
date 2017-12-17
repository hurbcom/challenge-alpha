package com.hotelurbano.desafio.detailshotels

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.hotelurbano.desafio.R
import com.hotelurbano.desafio.base.BaseActivity
import com.hotelurbano.desafio.base.util.CurrencyFormatter
import com.hotelurbano.desafio.detailshotels.adapter.AmenityAdapter
import com.hotelurbano.desafio.detailshotels.di.DaggerDetailsHotelActivityComponent
import com.hotelurbano.desafio.detailshotels.di.DetailsHotelActivityModule
import com.hotelurbano.desafio.detailshotels.presenter.DetailsHotelPresenter
import com.hotelurbano.desafio.detailshotels.view.DetailsHotelView
import com.hotelurbano.desafio.listhotels.model.HotelItem
import kotlinx.android.synthetic.main.activity_details_hotel.*
import kotlinx.android.synthetic.main.content_scrolling.*
import org.jetbrains.anko.longToast
import javax.inject.Inject

class DetailsHotelActivity : BaseActivity(), DetailsHotelView {

    @Inject
    lateinit var presenter: DetailsHotelPresenter

    private lateinit var listAdapter: AmenityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_hotel)
        setSupportActionBar(toolbar);

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val item = intent.extras!!.get("Hotel") as HotelItem

        initAdapter(item)

        presenter.setDetails(item)
    }

    override fun onActivityInject() {
        DaggerDetailsHotelActivityComponent.builder().appComponent(getAppcomponent())
            .detailsHotelActivityModule(DetailsHotelActivityModule())
            .build()
            .inject(this)

        presenter.attachView(this)
    }

    private fun initAdapter(item: HotelItem) {
        listAdapter = AmenityAdapter(item.amenities.take(3))

        with(hotelList) {
            layoutManager = LinearLayoutManager(this@DetailsHotelActivity)
            adapter = listAdapter
        }
    }

    override fun setDetails(item: HotelItem) {
        supportActionBar?.title = item.name

        hotelGallery.setImageURI(item.gallery[0].url)
        hotelDescription.text = if (item.description.isNotEmpty()) item.description else getString(R.string.lorem_ipsum)
        hotelAmount.text = CurrencyFormatter.getValue(item.price.amount)

        val location = "${item.address.city} - ${item.address.state}"
        hotelLocation.text = location

        hotelReserve.setOnClickListener {
            longToast("Reservado ${item.name} com sucesso!")
            finish()
        }
    }
}
