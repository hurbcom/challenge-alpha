package com.example.challenge_alpha.ui.results

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge_alpha.R

class ResultsFragment : Fragment() {

    private lateinit var resultsViewModel: ResultsViewModel
    private lateinit var progressBar: ProgressBar
    private val args: ResultsFragmentArgs by navArgs()
    private lateinit var recyclerResult: RecyclerView
    private val TAG = "HurbCall"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        resultsViewModel = ViewModelProvider(this).get(ResultsViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_results, container, false)
        progressBar = root.findViewById(R.id.indeterminateBar)

        resultsViewModel.search(args.queryString)


        recyclerResult = root.findViewById(R.id.recyclerResult)
        recyclerResult.layoutManager = LinearLayoutManager(root.context)
        val adapter = ResultsAdapter()
        recyclerResult.adapter = adapter


        resultsViewModel.resultDetailLive.observe(this, Observer {
            Log.d(TAG, "HOTELS $it")
            adapter.submitList(it)
            resultsViewModel.progressBar(false)
        })

        resultsViewModel.errorLive.observe(this, Observer {
            Log.d(TAG, "Error $it")
            resultsViewModel.progressBar(false)
        })

        resultsViewModel.progressBar.observe(this, Observer { isLoading ->
            progressBar(isLoading)

        })

        return root
    }

    private fun progressBar(visible: Boolean) {

        if (visible) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }

    }

}