package com.hurb.challengealpha.ui.search

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.test.espresso.IdlingResource
import com.google.android.material.snackbar.Snackbar
import com.hurb.challengealpha.R
import com.hurb.challengealpha.databinding.ActivitySearchBinding
import com.hurb.challengealpha.idlingresource.SimpleIdlingResource
import com.hurb.challengealpha.viewmodel.SearchViewModel


class SearchActivity : AppCompatActivity(),
    OnLastItemHandler {

    private lateinit var binding: ActivitySearchBinding
    private val adapter = SearchAdapter(this, this)
    private var mIdlingResource: SimpleIdlingResource = SimpleIdlingResource()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateUi()

        val model = ViewModelProvider(this).get(SearchViewModel::class.java)

        //Updates adapter when data is posted to view model
        model.getSearch().observe(this, Observer { results ->
            if (results == null || results.isEmpty()) {
                binding.emptyFl.visibility = View.VISIBLE
                binding.searchRv.visibility = View.GONE
                adapter.results = mutableListOf()
            } else {
                binding.emptyFl.visibility = View.GONE
                binding.searchRv.visibility = View.VISIBLE
                adapter.results = results.toMutableList()
            }
            adapter.notifyDataSetChanged()
        })

        //Updates progress bar when view model is loading
        model.isLoading().observe(this, Observer { isLoading ->
            if (isLoading) {
                mIdlingResource.setIdleState(false)
                binding.loadingPb.visibility = View.VISIBLE
                binding.contentLl.visibility = View.GONE
            } else {
                binding.loadingPb.visibility = View.GONE
                binding.contentLl.visibility = View.VISIBLE
                mIdlingResource.setIdleState(true)
            }
        })


        binding.searchBt.setOnClickListener { v ->
            onSearchClick(v)
        }

        //Allows search when pressing "Enter" on keyboard
        binding.searchEt.setOnEditorActionListener { v, actionId, _ ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    onSearchClick(v)
                    true
                }
                else -> false
            }
        }

        binding.clearBt.setOnClickListener {
            binding.searchEt.text.clear()
        }
    }

    //Prepares recyclerview for data
    private fun updateUi() {
        val layoutManager = GridLayoutManager(this, 2)
        binding.searchRv.layoutManager = layoutManager
        binding.searchRv.adapter = adapter
    }

    //Loads a new search
    private fun onSearchClick(view: View) {
        val query = binding.searchEt.text.toString()
        if (query.isEmpty() || query.isBlank()) {
            Snackbar.make(
                binding.contentLl, getString(R.string.type_something_snackbar),
                Snackbar.LENGTH_SHORT
            ).show()
        } else {
            val model = ViewModelProvider(this).get(SearchViewModel::class.java)
            model.loadSearch(query)
        }
        val imm: InputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    //Called from adapter when it is on last item
    override fun onLastItem() {
        val model = ViewModelProvider(this).get(SearchViewModel::class.java)
        model.loadMore()
    }

    @VisibleForTesting
    fun getIdlingResource(): IdlingResource {
        return mIdlingResource
    }
}
