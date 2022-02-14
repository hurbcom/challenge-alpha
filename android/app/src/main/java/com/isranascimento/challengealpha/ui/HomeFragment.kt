package com.isranascimento.challengealpha.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.isranascimento.challengealpha.R
import com.isranascimento.challengealpha.databinding.HomeFragmentBinding
import com.isranascimento.hotels.ui.fragment.HotelListFragment

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
        setupHomeToolbar()
        childFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, HotelListFragment())
            .commit()
    }

    private fun setupHomeToolbar() {
        binding.root.findViewById<Toolbar>(R.id.toolbar).apply {
            this.title = getString(R.string.app_name)
        }
    }
}