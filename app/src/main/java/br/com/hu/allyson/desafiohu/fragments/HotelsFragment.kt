package br.com.hu.allyson.desafiohu.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import br.com.hu.allyson.desafiohu.R
import br.com.hu.allyson.desafiohu.domain.Hotels
import br.com.hu.allyson.desafiohu.ui.hotels.HotelsViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class HotelsFragment : Fragment() {
    private lateinit var viewModel: HotelsViewModel

    private val observerHotels = Observer<List<Hotels>>{
        Toast.makeText(context, "${it.size}", Toast.LENGTH_LONG).show()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hotels, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(HotelsViewModel::class.java)
        viewModel.getHotels().observe(this, observerHotels)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.getHotels().removeObserver(observerHotels)

    }

}
