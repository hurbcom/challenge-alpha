package br.com.hu.allyson.desafiohu

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import br.com.hu.allyson.desafiohu.adapter.MainTabsAdapter
import br.com.hu.allyson.desafiohu.fragments.HotelsFragment
import br.com.hu.allyson.desafiohu.fragments.PackagesFragment
import kotlinx.android.synthetic.main.main_activity.*


class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        setupViewPager(pages)
        tab.setupWithViewPager(pages)


    }

    fun setupViewPager(viewPager: ViewPager) {
        var adapter = MainTabsAdapter(supportFragmentManager)
        adapter.addFragment(HotelsFragment(), "Hotéis")
        adapter.addFragment(PackagesFragment(), "Pacotes")
        viewPager.adapter = adapter;
    }


}
