package com.edufelip.challengealpha.presentation.fragments.general_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.edufelip.challengealpha.databinding.FragmentGeneralListMenuBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GeneralListMenuFragment @Inject constructor(
    val adapter: GeneralListMenuItemAdapter,
    var mGeneralListMenuViewModel: GeneralListMenuViewModel? = null
) : Fragment() {
    private var _binding: FragmentGeneralListMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGeneralListMenuBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        mGeneralListMenuViewModel =
            mGeneralListMenuViewModel ?: ViewModelProvider(requireActivity()).get(
                GeneralListMenuViewModel::class.java
            )

        return binding.root
    }
}