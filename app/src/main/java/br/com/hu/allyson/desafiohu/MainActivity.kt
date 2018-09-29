package br.com.hu.allyson.desafiohu

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import br.com.hu.allyson.desafiohu.domain.Result
import br.com.hu.allyson.desafiohu.mvp.interfaces.APIHotels
import br.com.hu.allyson.desafiohu.mvp.presenter.PresenterHotels
import br.com.hu.allyson.desafiohu.network.HotelsManager
import br.com.hu.allyson.desafiohu.ui.hotels.HotelsFragment
import br.com.hu.allyson.desafiohu.ui.hotels.HotelsViewModel
import br.com.hu.allyson.desafiohu.ui.hotels.PackagesFragment
import br.com.hu.allyson.desafiohu.ui.hotels.adapter.MainTabsAdapter
import kotlinx.android.synthetic.main.error_screen.*
import kotlinx.android.synthetic.main.main_activity.*


class MainActivity : FragmentActivity(), APIHotels.ViewHotelsImpl {

    private lateinit var presenter: PresenterHotels
    private lateinit var viewModel: HotelsViewModel
    private lateinit var result: Result

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        setupViewPager(pages)
        tab.setupWithViewPager(pages)
        viewModel = ViewModelProviders.of(this).get(HotelsViewModel::class.java)
        presenter = PresenterHotels()
        try_again.setOnClickListener {
            progress_bar.visibility = View.VISIBLE
            error_page.visibility = View.INVISIBLE
            requestHotelsStart()
        }
        requestHotelsStart()

    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = MainTabsAdapter(supportFragmentManager)
        adapter.addFragment(HotelsFragment(), "Hotéis")
        adapter.addFragment(PackagesFragment(), "Pacotes")
        viewPager.adapter = adapter
    }

    override fun requestHotelsStart() {
        presenter.bind(this, HotelsManager("Rio de Janeiro"))
        presenter.requestHotelsStart()
    }

    override fun requestHotelsSucess(result: Result) {
        this.result = result
        viewModel.getHotels().value = presenter.buildListHotels(result)
        viewModel.getPackages().value = presenter.buildListPackage(result)
        Log.e("OK", "Ok")
    }

    override fun requestHotelsError() {
        showError()
    }

    fun showPages() {
        pages.visibility = View.VISIBLE
        progress_bar.visibility = View.INVISIBLE
        error_page.visibility = View.INVISIBLE
    }

    private fun showError() {
        pages.visibility = View.INVISIBLE
        progress_bar.visibility = View.INVISIBLE
        error_page.visibility = View.VISIBLE
    }


}
