package br.com.hu.allyson.desafiohu.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

import br.com.hu.allyson.desafiohu.fragments.HotelsFragment
import br.com.hu.allyson.desafiohu.fragments.PackagesFragment

class MainTabsAdapter(fm: FragmentManager
                      ): FragmentPagerAdapter(fm) {

    val fragments = mutableListOf<Fragment>()
    val titles = mutableListOf<String>()
    fun addFragment(fragment: Fragment, title: String){
        fragments.add(fragment)
        titles.add(title)
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }
}