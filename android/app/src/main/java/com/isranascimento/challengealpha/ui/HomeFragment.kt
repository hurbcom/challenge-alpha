package com.isranascimento.challengealpha.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.isranascimento.challengealpha.R
import com.isranascimento.challengealpha.databinding.HomeFragmentBinding
import com.isranascimento.hotelslist.ui.fragment.HotelListFragment

class HomeFragment: Fragment() {
    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = HomeFragmentBinding.inflate(inflater, container, false)
            .also {
                binding = it
            }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.title = getString(R.string.app_name)
        childFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, HotelListFragment())
            .commit()
    }
}