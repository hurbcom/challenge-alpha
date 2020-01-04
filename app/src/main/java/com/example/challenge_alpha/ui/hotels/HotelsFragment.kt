package com.example.challenge_alpha.ui.hotels

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.challenge_alpha.R

class HotelsFragment : Fragment(), View.OnClickListener {

    private lateinit var hotelsViewModel: HotelsViewModel
    private lateinit var searchInput: EditText
    private lateinit var _context: Context
    private val TAG = "HurbCall"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        hotelsViewModel = ViewModelProvider(this).get(HotelsViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_hotels, container, false)
        _context = root.context
        searchInput = root.findViewById(R.id.search_input)
        searchInput.setText(hotelsViewModel.lastQuery())

        setListener(root)


        return root
    }

    private fun setListener(view: View) {
        val searchButton = view.findViewById<Button>(R.id.search_button)
        searchButton.setOnClickListener(this)

    }

    override fun onClick(view: View) {

        val navController = findNavController()

        when (view.id) {
            R.id.search_button -> {
                searchInput.text.trim().let {
                    if (it.isNotEmpty()) {
                        hotelsViewModel.search(it.toString())
                        val action = HotelsFragmentDirections.hotelsToResults(it.toString())
                        navController.navigate(action)
                    } else {
                        Toast.makeText(
                            _context,
                            getString(R.string.search_error),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            }

        }

    }

}