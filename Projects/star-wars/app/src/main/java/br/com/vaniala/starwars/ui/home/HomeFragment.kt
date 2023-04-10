package br.com.vaniala.starwars.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import br.com.vaniala.starwars.core.State
import br.com.vaniala.starwars.databinding.FragmentHomeBinding
import br.com.vaniala.starwars.domain.model.Category
import br.com.vaniala.starwars.ui.home.recycler.CategoryListAdapter
import br.com.vaniala.starwars.ui.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 10/04/23.
 *
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!
    private lateinit var adapter: CategoryListAdapter

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding
            .inflate(
                inflater,
                container,
                false,
            )
        return binding.root
    }

    private fun initRecyclerView() {
        adapter = CategoryListAdapter()
        binding.fragmentHomeRecycler.adapter = adapter
    }

    private fun setStateHome() {
        viewModel.categories.onEach { homeState ->
            when (homeState) {
                is State.Loading -> Toast.makeText(context, "carregando", Toast.LENGTH_SHORT).show()
                is State.Empty -> Toast.makeText(context, "empty", Toast.LENGTH_SHORT).show()
                is State.Error -> Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
                is State.Success<*> -> {
                    val categories: List<Category> = homeState.result.filterIsInstance<Category>()
                    adapter.submitList(categories)
                }
            }
        }.launchIn(lifecycleScope)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        setStateHome()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        timber.log.Timber.d("onDestroyView")
    }
}
