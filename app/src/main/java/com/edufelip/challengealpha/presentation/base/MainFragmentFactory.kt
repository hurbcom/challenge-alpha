package com.edufelip.challengealpha.presentation.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.edufelip.challengealpha.presentation.fragments.category_list.category_list_film.CategoryListFilmFragment
import com.edufelip.challengealpha.presentation.fragments.category_list.category_list_film.CategoryListFilmItemAdapter
import com.edufelip.challengealpha.presentation.fragments.category_list.category_list_people.CategoryListPeopleFragment
import com.edufelip.challengealpha.presentation.fragments.category_list.category_list_people.CategoryListPeopleItemAdapter
import com.edufelip.challengealpha.presentation.fragments.category_list.category_list_planet.CategoryListPlanetFragment
import com.edufelip.challengealpha.presentation.fragments.category_list.category_list_planet.CategoryListPlanetItemAdapter
import com.edufelip.challengealpha.presentation.fragments.category_list.category_list_specie.CategoryListSpecieFragment
import com.edufelip.challengealpha.presentation.fragments.category_list.category_list_specie.CategoryListSpecieItemAdapter
import com.edufelip.challengealpha.presentation.fragments.category_list.category_list_starship.CategoryListStarshipFragment
import com.edufelip.challengealpha.presentation.fragments.category_list.category_list_starship.CategoryListStarshipItemAdapter
import com.edufelip.challengealpha.presentation.fragments.category_list.category_list_vehicle.CategoryListVehicleFragment
import com.edufelip.challengealpha.presentation.fragments.category_list.category_list_vehicle.CategoryListVehicleItemAdapter
import com.edufelip.challengealpha.presentation.fragments.general_list.GeneralListMenuFragment
import com.edufelip.challengealpha.presentation.fragments.general_list.GeneralListMenuItemAdapter
import javax.inject.Inject

class MainFragmentFactory @Inject constructor(
    private val generalListMenuItemAdapter: GeneralListMenuItemAdapter,
    private val categoryListPeopleItemAdapter: CategoryListPeopleItemAdapter,
    private val categoryListFilmItemAdapter: CategoryListFilmItemAdapter,
    private val categoryListPlanetItemAdapter: CategoryListPlanetItemAdapter,
    private val categoryListSpecieItemAdapter: CategoryListSpecieItemAdapter,
    private val categoryListStarshipItemAdapter: CategoryListStarshipItemAdapter,
    private val categoryListVehicleItemAdapter: CategoryListVehicleItemAdapter,
) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className) {
            GeneralListMenuFragment::class.java.name -> {
                GeneralListMenuFragment(generalListMenuItemAdapter)
            }
            CategoryListFilmFragment::class.java.name -> {
                CategoryListFilmFragment(categoryListFilmItemAdapter)
            }
            CategoryListPeopleFragment::class.java.name -> {
                CategoryListPeopleFragment(categoryListPeopleItemAdapter)
            }
            CategoryListPlanetFragment::class.java.name -> {
                CategoryListPlanetFragment(categoryListPlanetItemAdapter)
            }
            CategoryListSpecieFragment::class.java.name -> {
                CategoryListSpecieFragment(categoryListSpecieItemAdapter)
            }
            CategoryListStarshipFragment::class.java.name -> {
                CategoryListStarshipFragment(categoryListStarshipItemAdapter)
            }
            CategoryListVehicleFragment::class.java.name -> {
                CategoryListVehicleFragment(categoryListVehicleItemAdapter)
            }
            else -> super.instantiate(classLoader, className)
        }
    }
}