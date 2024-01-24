package com.br.myapplication.ui.species

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.br.myapplication.R
import com.br.myapplication.data.model.Specie
import com.br.myapplication.databinding.FragmentSpeciesBinding
import com.br.myapplication.extensions.hide
import com.br.myapplication.extensions.visible
import com.br.myapplication.ui.detail.DetailFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SpeciesFragment : Fragment() {

    private var _binding: FragmentSpeciesBinding? = null

    private val binding get() = _binding!!

    private val viewModel: SpeciesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSpeciesBinding.inflate(inflater, container, false)

        initObservables()

        binding.searchET.doOnTextChanged { text, start, before, count ->
            lifecycleScope.launch {
                viewModel.setFilter(text.toString())
            }
        }

        return binding.root
    }

    private fun initObservables() {

        val adapter = SpeciesAdapter(action = {
            viewModel.updateSpecie(it)
        },
            callDetail = {
                callDetail(it)
            }
        )
        binding.speciesListRV.adapter = adapter
        binding.speciesListRV.layoutManager = LinearLayoutManager(requireContext(), VERTICAL, false)
        viewModel.specieList.observe(viewLifecycleOwner) {

            lifecycleScope.launch {

                adapter.submitData(it)
            }
            if (it != null) {
                binding.progressBar.hide()
                binding.speciesListRV.visible()
            }
        }

        viewModel.filteredSpecieList.observe(viewLifecycleOwner) {

            lifecycleScope.launch {

                adapter.submitData(it)
            }
        }
    }

    private fun callDetail(specie: Specie) {

        val fragmentDetail = DetailFragment()
        val bundle = Bundle().apply {
            putString("first_item", specie.name)
            putString("second_item", specie.created)
            putString("third_item", specie.language)
            putString("fourth_item", specie.designation)
            putString("fifth_item", specie.classification)
            putString("image", specie.image)

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