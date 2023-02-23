package com.example.test.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.test.R
import com.example.test.databinding.FragmentHomeBinding
import com.example.test.presentation.home.adapters.CategoriesPagerAdapter
import com.example.test.presentation.home.category.CategoryFragment
import com.example.test.presentation.home.category.PeopleViewModel
import com.example.test.presentation.home.category.PlanetsViewModel
import com.example.test.presentation.home.category.StarshipsViewModel
import com.example.test.utils.Constants.PEOPLE_HOME_INDEX
import com.example.test.utils.Constants.PLANETS_HOME_INDEX
import com.example.test.utils.Constants.STARSHIPS_HOME_INDEX
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val peopleViewModel: PeopleViewModel by viewModels()
    private val planetsViewModel: PlanetsViewModel by viewModels()
    private val starshipsViewModel: StarshipsViewModel by viewModels()
    private val homeFragments by lazy {
        arrayListOf(
            CategoryFragment.newInstance(peopleViewModel),
            CategoryFragment.newInstance(planetsViewModel),
            CategoryFragment.newInstance(starshipsViewModel)
        )
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
                activity?.run {
                    adapter = CategoriesPagerAdapter(
                        homeFragments, supportFragmentManager, lifecycle
                    )
                }

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
                        vpCategories.currentItem = PEOPLE_HOME_INDEX
                        return@setOnItemSelectedListener true
                    }
                    R.id.itemPlanets -> {
                        vpCategories.currentItem = PLANETS_HOME_INDEX
                        return@setOnItemSelectedListener true
                    }
                    R.id.itemStarships -> {
                        vpCategories.currentItem = STARSHIPS_HOME_INDEX
                        return@setOnItemSelectedListener true
                    }
                    else -> return@setOnItemSelectedListener false
                }
            }
        }
    }
}