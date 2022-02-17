package com.isranascimento.challengealpha.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.isranascimento.challengealpha.R
import com.isranascimento.challengealpha.databinding.HomeFragmentBinding
import com.isranascimento.core.fragment.BaseToolbarFragment
import com.isranascimento.hotels.ui.fragment.HotelListFragment
import com.isranascimento.lastviewed.ui.fragment.LastViewedFragment

class HomeFragment: BaseToolbarFragment() {
    private lateinit var binding: HomeFragmentBinding

    private val hotelsFragment = HotelListFragment()
    private val lastViewedFragment = LastViewedFragment()
    private var activeFragment: Fragment? = null

    override fun getToolbarTitle(): String = getString(R.string.app_name)

    override fun hasNavigationItem() = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = HomeFragmentBinding.inflate(inflater, container, false)
            .also {
                binding = it
            }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activeFragment = lastViewedFragment

        childFragmentManager.beginTransaction()
            .add(R.id.fragment_container, hotelsFragment)
            .hide(hotelsFragment)
            .add(R.id.fragment_container, lastViewedFragment)
            .commit()
        binding.bottomNavigationContainer.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_hotel -> {
                    childFragmentManager
                        .beginTransaction()
                        .hide(activeFragment!!)
                        .show(hotelsFragment)
                        .commit()
                    activeFragment = hotelsFragment
                    return@setOnItemSelectedListener true

                }
                R.id.menu_history -> {
                    childFragmentManager
                        .beginTransaction()
                        .hide(activeFragment!!)
                        .show(lastViewedFragment)
                        .commit()
                    activeFragment = lastViewedFragment
                    return@setOnItemSelectedListener true

                }
            }
            return@setOnItemSelectedListener false
        }
    }

    private fun replaceFragment(newFragment: Fragment) {
        childFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, newFragment)
            .commit()

    }
}