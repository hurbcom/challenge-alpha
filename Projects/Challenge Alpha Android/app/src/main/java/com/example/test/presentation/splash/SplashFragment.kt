package com.example.test.presentation.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.core.base.BaseFragment
import com.example.test.R
import com.example.test.databinding.FragmentSplashBinding
import com.example.test.utils.Constants.SPLASH_NAV_DELAY

class SplashFragment : BaseFragment() {
    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        hasToolbar(false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        Handler(Looper.getMainLooper()).postDelayed(
            { findNavController().navigate(R.id.action_splash_to_homeFragment) }, SPLASH_NAV_DELAY
        )
    }
}