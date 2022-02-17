package com.isranascimento.lastviewed.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.isranascimento.lastviewed.databinding.LastViewedFragmentBinding

class LastViewedFragment: Fragment() {
    private lateinit var binding: LastViewedFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return LastViewedFragmentBinding.inflate(
            inflater, container, false
        ).also {
            binding = it
        }.root
    }
}