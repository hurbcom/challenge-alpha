package com.br.myapplication.ui.planets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.br.myapplication.R
import com.br.myapplication.data.model.Planet

import com.br.myapplication.databinding.FragmentPlanetsBinding
import com.br.myapplication.extensions.hide
import com.br.myapplication.extensions.visible
import com.br.myapplication.ui.detail.DetailFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlanetsFragment : Fragment() {

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

        binding.searchET.doOnTextChanged { text, start, before, count ->
            lifecycleScope.launch {
                viewModel.setFilter(text.toString())
            }
        }

        return binding.root
    }

    private fun initObservables() {

        val adapter = PlanetsAdapter(action = {
            viewModel.updatePlanet(it)
        },
            callDetail = {
                callDetail(it)
            }
        )
        binding.planetsListRV.adapter = adapter
        binding.planetsListRV.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        viewModel.planetList.observe(viewLifecycleOwner) {

            lifecycleScope.launch {

                adapter.submitData(it)
            }
            if (it != null) {
                binding.progressBar.hide()
                binding.planetsListRV.visible()
            }
        }
        viewModel.filteredPlanetList.observe(viewLifecycleOwner) {

            lifecycleScope.launch {

                adapter.submitData(it)
            }
        }
    }

    private fun callDetail(planet: Planet) {

        val fragmentDetail = DetailFragment()
        val bundle = Bundle().apply {
            putString("first_item", planet.name)
            putString("second_item", planet.created)
            putString("third_item", planet.climate)
            putString("fourth_item", planet.gravity)
            putString("fifth_item", planet.population)
            putString("image", planet.image)

        }

        fragmentDetail.arguments = bundle

        val fragmentManager = requireActivity().supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment_activity_main, fragmentDetail)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}