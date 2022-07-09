package com.edufelip.challengealpha.presentation.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.edufelip.challengealpha.presentation.fragments.general_list.GeneralListMenuFragment
import com.edufelip.challengealpha.presentation.fragments.general_list.GeneralListMenuItemAdapter
import javax.inject.Inject

class MainFragmentFactory @Inject constructor(
    private val generalListMenuItemAdapter: GeneralListMenuItemAdapter
) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className) {
            GeneralListMenuFragment::class.java.name -> {
                GeneralListMenuFragment(generalListMenuItemAdapter)
            }
            else -> super.instantiate(classLoader, className)
        }
    }
}