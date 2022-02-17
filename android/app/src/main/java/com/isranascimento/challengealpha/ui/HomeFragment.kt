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
        setupInitialFragmentManager()
        binding.bottomNavigationContainer.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_hotel -> {
                    replaceFragment(hotelsFragment)
                    return@setOnItemSelectedListener true

                }
                R.id.menu_history -> {
                    replaceFragment(lastViewedFragment)
                    return@setOnItemSelectedListener true

                }
            }
            return@setOnItemSelectedListener false
        }
    }

    private fun setupInitialFragmentManager() {
        activeFragment = hotelsFragment

        childFragmentManager.beginTransaction()
            .add(R.id.fragment_container, lastViewedFragment)
            .hide(lastViewedFragment)
            .add(R.id.fragment_container, hotelsFragment)
            .commit()
    }

    private fun replaceFragment(newFragment: Fragment) {
        childFragmentManager
            .beginTransaction()
            .hide(activeFragment!!)
            .show(newFragment)
            .commit()
        activeFragment = newFragment
    }
}