package com.example.starwars.presentation.feature.listpeople

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.starwars.databinding.FragmentListItemBinding
import com.example.starwars.presentation.adapter.Adapter
import com.example.starwars.presentation.feature.ListItemFragment
import com.example.starwars.presentation.model.Item
import com.example.starwars.presentation.model.People
import com.example.starwars.presentation.safeNavigate
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListPeopleFragment : ListItemFragment() {
    override val adapter: Adapter by inject()
    override val viewModel: ListPeopleViewModel by viewModel()

    override lateinit var binding: FragmentListItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getItems("1")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun nextActivity(item: Item) {
        if (item is People) {
            findNavController().safeNavigate(
                ListPeopleFragmentDirections.actionListPeopleFragmentToDetailsPeopleFragment(
                    item
                )
            )
        }
    }
}
