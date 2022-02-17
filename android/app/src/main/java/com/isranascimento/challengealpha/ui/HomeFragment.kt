package com.isranascimento.challengealpha.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.isranascimento.challengealpha.R
import com.isranascimento.challengealpha.databinding.HomeFragmentBinding
import com.isranascimento.core.fragment.BaseToolbarFragment
import com.isranascimento.hotels.ui.fragment.HotelListFragment
import com.isranascimento.lastviewed.ui.fragment.LastViewedFragment

class HomeFragment: BaseToolbarFragment() {
    private lateinit var binding: HomeFragmentBinding

    private val tabsFragments = listOf<TabFragmentModel>(
        TabFragmentModel(
            title = R.string.hotels,
            icon = R.drawable.ic_outline_hotel,
            fragment = HotelListFragment()
        ),
        TabFragmentModel(
            title = R.string.last_viewed,
            icon = R.drawable.ic_history,
            fragment = LastViewedFragment()
        )
    )

    private val adapter by lazy {
        HomeViewPagerAdapter(childFragmentManager, lifecycle, tabsFragments.map {
            it.fragment
        })
    }

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
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            val tabFragmentModel = tabsFragments[position]
            tab.text = getString(tabFragmentModel.title)
            tab.icon = AppCompatResources.getDrawable(requireContext(), tabFragmentModel.icon)
        }.attach()
    }
}