package com.hurb.challengealpha

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.hurb.challengealpha.databinding.ActivitySearchBinding
import com.hurb.challengealpha.viewmodel.SearchViewModel


class SearchActivity : AppCompatActivity(), OnLastItemHandler {

    private lateinit var binding: ActivitySearchBinding
    private val adapter = SearchAdapter(this, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateUi()

        val model = ViewModelProvider(this).get(SearchViewModel::class.java)
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
        model.isLoading().observe(this, Observer { isLoading ->
            if (isLoading) {
                binding.loadingPb.visibility = View.VISIBLE
                binding.contentLl.visibility = View.GONE
            } else {
                binding.loadingPb.visibility = View.GONE
                binding.contentLl.visibility = View.VISIBLE
            }
        })
        binding.searchBt.setOnClickListener { v ->
            onSearchClick(v)
        }
        binding.searchEt.setOnEditorActionListener { v, actionId, event ->
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


    private fun updateUi() {
        val layoutManager = GridLayoutManager(this, 2)
        binding.searchRv.layoutManager = layoutManager
        binding.searchRv.adapter = adapter
    }

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

    override fun onLastItem() {
        val model = ViewModelProvider(this).get(SearchViewModel::class.java)
        model.loadMore()
    }
}
