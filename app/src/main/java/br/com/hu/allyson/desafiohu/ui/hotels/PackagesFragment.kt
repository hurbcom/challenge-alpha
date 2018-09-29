package br.com.hu.allyson.desafiohu.ui.hotels


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.hu.allyson.desafiohu.R
import br.com.hu.allyson.desafiohu.ui.hotels.adapter.PackageAdapter
import br.com.hu.allyson.desafiohu.domain.Hotels
import kotlinx.android.synthetic.main.layout_list_packages.*

/**
 * A simple [Fragment] subclass.
 *
 */
class PackagesFragment : Fragment() {

    private lateinit var viewModel: HotelsViewModel
    private lateinit var adapter: PackageAdapter


    private val observerPackages = Observer<List<Hotels>>{

        if(it.isNotEmpty()) {
            adapter.addItens(it)
            adapter.notifyDataSetChanged()
        }else{
            list_package.visibility = View.INVISIBLE
            page_no_data_package.visibility = View.VISIBLE
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_packages, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buildListPackage()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(HotelsViewModel::class.java)
        viewModel.getPackages().observe(this, observerPackages)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.getHotels().removeObserver(observerPackages)

    }

    fun buildListPackage(){
        adapter = PackageAdapter(context!!, listOf())
        val mLayoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        list_package.layoutManager = mLayoutManager
        list_package.adapter = adapter
        adapter.notifyDataSetChanged()
    }


}
