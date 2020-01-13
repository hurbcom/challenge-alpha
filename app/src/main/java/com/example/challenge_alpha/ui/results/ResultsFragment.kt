package com.example.challenge_alpha.ui.results

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge_alpha.R
import com.example.challenge_alpha.api.Result
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

    private lateinit var progressBar: ProgressBar
    private val args: ResultsFragmentArgs by navArgs()
    private lateinit var _context: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_results, container, false)
        _context = root.context

        progressBar = root.findViewById(R.id.indeterminateBar)

        resultsRecycler(root)

        resultsViewModel.progressBar.observe(this, Observer { isLoading ->
            progressBar(isLoading)
        })

        return root
    }

    private fun resultsRecycler(view: View) {

        val recyclerResult: RecyclerView = view.findViewById(R.id.recyclerResult)
        recyclerResult.layoutManager = LinearLayoutManager(_context)
        val adapter = ResultsAdapter()
        recyclerResult.adapter = adapter

        resultsViewModel.resultsDetailLive.observe(this, Observer { result ->

            when (result.status) {
                Result.Status.SUCCESS -> {
                    val sorted =
                        result?.data?.resultDetail?.sortedByDescending { list -> list.stars }
                    sorted.let { List ->
                        for (stars in 1..5) {
                            List?.firstOrNull { it.stars == stars.toFloat() }?.recyclerTitle = true
                        }
                        List?.firstOrNull { !it.hotelIs }?.recyclerTitle = true
                    }

                    result.data.let { adapter.submitList(sorted) }
                    resultsViewModel.progressBar(false)
                }
                Result.Status.LOADING -> {
                    resultsViewModel.progressBar(true)
                }
                Result.Status.ERROR -> {
                    resultsViewModel.progressBar(false)
                    Toast.makeText(
                        _context,
                        getString(R.string.results_error),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }


        })

    }

    private fun progressBar(visible: Boolean) {

        if (visible) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }

    }


}