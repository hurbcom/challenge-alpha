package app.recrutamento.android.challengealpha.ui.hotellist

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import app.recrutamento.android.challengealpha.R
import app.recrutamento.android.challengealpha.adapters.HotelAdapter
import app.recrutamento.android.challengealpha.adapters.general.BindingAdapters
import app.recrutamento.android.challengealpha.databinding.HotelListFragmentBinding
import app.recrutamento.android.challengealpha.model.hotel.Hotel
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*


class HotelListFragment : Fragment(), SearchView.OnQueryTextListener, View.OnClickListener {

    val vm: HotelListViewModel by viewModel()
    lateinit var searchView: SearchView
    var localText: String = ""
    lateinit var optStarFive: com.github.clans.fab.FloatingActionButton
    lateinit var optStarFour: com.github.clans.fab.FloatingActionButton
    lateinit var optStarThree: com.github.clans.fab.FloatingActionButton
    var isGridLayout: Boolean = false

    companion object {
        fun newInstance(): HotelListFragment {
            return HotelListFragment()
        }
    }

    override fun onCreateView(
        @NonNull inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: HotelListFragmentBinding =
            HotelListFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = vm

        isGridLayout = arguments?.getBoolean("isGridMode") ?: false

        binding.recyclerView.adapter =
            HotelAdapter(Collections.emptyList(), isGridLayout)

        if (isGridLayout) {
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                val manager = GridLayoutManager(
                    activity!!.applicationContext,
                    2,
                    GridLayoutManager.VERTICAL,
                    false
                )
                binding.recyclerView.layoutManager = manager
            } else {
                val manager = GridLayoutManager(
                    activity!!.applicationContext,
                    4,
                    GridLayoutManager.VERTICAL,
                    false
                )
                binding.recyclerView.layoutManager = manager
            }

        } else {
            binding.recyclerView.layoutManager = LinearLayoutManager(activity)
            binding.recyclerView.addItemDecoration(
                DividerItemDecoration(
                    binding.recyclerView.context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }

        searchView = binding.searchView as SearchView
        optStarFive = binding.optStarFive
        optStarFour = binding.optStarFour
        optStarThree = binding.optStarThree
        optStarFive.setOnClickListener(this)
        optStarFour.setOnClickListener(this)
        optStarThree.setOnClickListener(this)

        searchView.setOnQueryTextListener(this)
        searchView.isIconifiedByDefault = false

        val observer =
            Observer<MutableList<Hotel>> { t ->
                BindingAdapters.setItems(
                    binding.recyclerView,
                    t!!.toMutableList()
                )
            }

        vm.hotel.observe(viewLifecycleOwner, observer)

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        localText = "buzios"
        vm.load("buzios", "1", "", isGridLayout)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(local: String): Boolean {
        localText = local
        if (!localText.isEmpty())
            vm.load(local, "1", "", isGridLayout)
        return false
    }

    override fun onClick(v: View?) {

        when (v!!.id) {
            R.id.optStarFive -> vm.load(localText, "1", "5", isGridLayout)
            R.id.optStarFour -> vm.load(localText, "1", "4", isGridLayout)
            R.id.optStarThree -> vm.load(localText, "1", "3", isGridLayout)
        }
    }

}