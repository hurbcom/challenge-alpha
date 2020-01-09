package com.example.challenge_alpha.ui.results

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge_alpha.R
import com.example.challenge_alpha.api.Result
import com.example.challenge_alpha.di.Injectable
import com.example.challenge_alpha.di.injector
import com.example.challenge_alpha.di.viewModel
import javax.inject.Inject

class ResultsFragment : Fragment(), Injectable {



    private val resultsViewModel by viewModel(this) {
        injector.resultsViewModelFactory.create(args.queryString)
    }

    private lateinit var progressBar: ProgressBar
    private val args: ResultsFragmentArgs by navArgs()
    private lateinit var recyclerResult: RecyclerView
    private val TAG = "HurbCall"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_results, container, false)
        Log.d(TAG, args.queryString)

        progressBar = root.findViewById(R.id.indeterminateBar)

        recyclerResult = root.findViewById(R.id.recyclerResult)
        recyclerResult.layoutManager = LinearLayoutManager(root.context)
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
                    }

                    val teste = sorted?.filter { it.recyclerTitle }?.map { it.name }
                    Log.d("estrelas", "$teste")


                    result.data.let { adapter.submitList(sorted) }
                    resultsViewModel.progressBar(false)
                }
                Result.Status.LOADING -> {
                    resultsViewModel.progressBar(true)
                }
                Result.Status.ERROR -> {
                    resultsViewModel.progressBar(false)
                    Toast.makeText(
                        root.context,
                        "Aconteceu algo de errado, por favor tente novamente",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }


        })

        resultsViewModel.progressBar.observe(this, Observer { isLoading ->
            progressBar(isLoading)
        })

        return root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    private fun progressBar(visible: Boolean) {

        if (visible) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }

    }


}