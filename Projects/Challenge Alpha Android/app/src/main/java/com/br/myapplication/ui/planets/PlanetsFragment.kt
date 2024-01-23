package com.br.myapplication.ui.planets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.br.myapplication.databinding.FragmentPlanetsBinding
import com.br.myapplication.extensions.hide
import com.br.myapplication.extensions.visible
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlanetsFragment: Fragment() {

    private var _binding: FragmentPlanetsBinding? = null

    private val binding get() = _binding!!

    private val viewModel: PlanetsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPlanetsBinding.inflate(inflater, container, false)

        initObservables()

        return binding.root
    }

    private fun initObservables() {

        val adapter = PlanetsAdapter()
        binding.planetsListRV.adapter = adapter
        binding.planetsListRV.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        viewModel.planetList.observe(viewLifecycleOwner){

            lifecycleScope.launch {

                adapter.submitData(it)
            }
            if (it != null) {
                binding.progressBar.hide()
                binding.planetsListRV.visible()
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}