package com.example.challenge_alpha.ui.results

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.challenge_alpha.R
import com.example.challenge_alpha.databinding.FragmentResultsBinding
import com.example.challenge_alpha.di.Injectable
import com.example.challenge_alpha.di.injector
import com.example.challenge_alpha.di.viewModel

/**
 * Fragmento responsável por mostrar os resultados das pesquisas feitas pelo usuário.
 * [resultsRecycler] é responsável por configurar a recyclerview com os resultados.
 */
class ResultsFragment : Fragment(), Injectable {


    private val resultsViewModel by viewModel(this) {
        injector.resultsViewModelFactory.create(args.queryString)
    }


    private val args: ResultsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentResultsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_results, container, false)
        binding.lifecycleOwner = this
        binding.results = resultsViewModel.resultsDetailLive()

        return binding.root
    }



}