package com.example.test.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.test.R
import com.example.test.databinding.FragmentHomeBinding
import com.example.test.presentation.home.adapters.CategoriesPagerAdapter
import com.example.test.presentation.home.people.PeopleFragment
import com.example.test.presentation.home.planets.PlanetsFragment
import com.example.test.presentation.home.starships.StarshipsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val homeFragments by lazy {
        arrayListOf(
            PeopleFragment.newInstance(),
            PlanetsFragment.newInstance(),
            StarshipsFragment.newInstance()
        )
    }
    private val categoriesPagerAdapter: CategoriesPagerAdapter? by lazy {
        activity?.run {
            CategoriesPagerAdapter(homeFragments, supportFragmentManager, lifecycle)
        } ?: null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        with(binding) {
            vpCategories.run {
                adapter = categoriesPagerAdapter
                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        navHomes.menu.getItem(position).isChecked = false
                    }
                })
            }
            navHomes.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.itemPeople -> {
                        vpCategories.currentItem = 0
                        return@setOnItemSelectedListener true
                    }
                    R.id.itemPlanets -> {
                        vpCategories.currentItem = 1
                        return@setOnItemSelectedListener true
                    }
                    R.id.itemStarships -> {
                        vpCategories.currentItem = 2
                        return@setOnItemSelectedListener true
                    }
                    else -> return@setOnItemSelectedListener false
                }
            }

        }
    }
}