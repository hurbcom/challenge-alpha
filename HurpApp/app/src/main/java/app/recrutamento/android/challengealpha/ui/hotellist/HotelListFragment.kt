package app.recrutamento.android.challengealpha.ui.hotellist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import app.recrutamento.android.challengealpha.adapters.HotelAdapter
import app.recrutamento.android.challengealpha.adapters.general.BindingAdapters
import app.recrutamento.android.challengealpha.databinding.HotelListFragmentBinding
import app.recrutamento.android.challengealpha.model.hotel.Hotel
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class HotelListFragment : Fragment() {

    val vm: HotelListViewModel by viewModel()

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
        binding.recyclerView.adapter =
            HotelAdapter(Collections.emptyList())

        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                DividerItemDecoration.VERTICAL
            )
        )

        val observer =
            Observer<MutableList<Hotel>> { t -> BindingAdapters.setItems(binding.recyclerView, t!!.toMutableList()) }

        vm.hotel.observe(viewLifecycleOwner, observer)

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        vm.load("buzios","1")
    }
}