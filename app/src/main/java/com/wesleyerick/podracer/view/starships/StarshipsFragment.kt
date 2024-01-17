package com.wesleyerick.podracer.view.starships

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wesleyerick.podracer.R

class StarshipsFragment : Fragment() {

    companion object {
        fun newInstance() = StarshipsFragment()
    }

    private lateinit var viewModel: StarshipsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_starships, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(StarshipsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}