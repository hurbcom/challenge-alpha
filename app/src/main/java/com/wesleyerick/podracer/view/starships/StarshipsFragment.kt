package com.wesleyerick.podracer.view.starships

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.wesleyerick.podracer.databinding.FragmentStarshipsBinding
import com.wesleyerick.podracer.util.gone
import com.wesleyerick.podracer.util.listener
import org.koin.androidx.viewmodel.ext.android.viewModel

class StarshipsFragment : Fragment() {

    private lateinit var binding: FragmentStarshipsBinding
    private val viewModel by viewModel<StarshipsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStarshipsBinding.inflate(inflater, container, false)
        setupViewModel()
        return binding.root
    }

    private fun setupViewModel() {
        viewModel.getList()
        viewModel.apply {
            list.listener(viewLifecycleOwner) {
                println("lista starships -> $it")
            }

            onError.listener(viewLifecycleOwner) {
                if (it.isNotEmpty()) {
                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT)
                        .show()
                }
//                binding.vehiclesProgressBar.gone()
            }
        }
    }

}