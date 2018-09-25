package br.com.hu.allyson.desafiohu

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import br.com.hu.allyson.desafiohu.adapter.MainTabsAdapter
import br.com.hu.allyson.desafiohu.domain.Hotels
import br.com.hu.allyson.desafiohu.domain.Result
import br.com.hu.allyson.desafiohu.fragments.HotelsFragment
import br.com.hu.allyson.desafiohu.fragments.PackagesFragment
import br.com.hu.allyson.desafiohu.mvp.interfaces.APIHotels
import br.com.hu.allyson.desafiohu.mvp.presenter.PresenterHotels
import br.com.hu.allyson.desafiohu.network.HotelsManager
import br.com.hu.allyson.desafiohu.ui.hotels.HotelsViewModel
import kotlinx.android.synthetic.main.main_activity.*


class MainActivity : FragmentActivity(), APIHotels.ViewHotelsImpl {

    private lateinit var presenter: PresenterHotels
    private var hotels = listOf<Hotels>()
    private var packages = listOf<Hotels>()
    private lateinit var viewModel: HotelsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        setupViewPager(pages)
        tab.setupWithViewPager(pages)

        viewModel = ViewModelProviders.of(this).get(HotelsViewModel::class.java)

        requestHotelsStart()

    }

    fun setupViewPager(viewPager: ViewPager) {
        var adapter = MainTabsAdapter(supportFragmentManager)
        adapter.addFragment(HotelsFragment(), "Hotéis")
        adapter.addFragment(PackagesFragment(), "Pacotes")
        viewPager.adapter = adapter;
    }

    override fun requestHotelsStart() {
        presenter = PresenterHotels()
        presenter.bind(this, HotelsManager("Rio de Janeiro"))
        presenter.requestHotelsStart()
    }

    override fun requestHotelsSucess(result: Result) {
        viewModel.getHotels().value = presenter.buildListHotels(result)
        viewModel.getPackages().value = presenter.buildListPackage(result)
        //packages = presenter.buildListPackage(result)
        Log.e("OK", "Ok")
    }

    override fun requestHotelsError() {

    }
}
