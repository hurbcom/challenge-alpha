package br.com.vaniala.starwars.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import br.com.vaniala.starwars.R
import br.com.vaniala.starwars.core.State
import br.com.vaniala.starwars.databinding.FragmentHomeBinding
import br.com.vaniala.starwars.domain.model.Category
import br.com.vaniala.starwars.ui.home.adapter.CategoryListAdapter
import br.com.vaniala.starwars.ui.viewmodel.SharedViewModel
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

    private val viewModel: SharedViewModel by activityViewModels()

    private val findNavController by lazy {
        findNavController()
    }
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        setStateHome()
    }

    private fun initRecyclerView() {
        adapter = CategoryListAdapter()
        adapter.onItemClickListener = {
            when (it.name) {
                "films" -> {
                    findNavController.navigate(
                        HomeFragmentDirections.actionHomeToFilms(),
                    )
                }
                "people" -> {
                    findNavController.navigate(
                        HomeFragmentDirections.actionHomeToCharacters(),
                    )
                }
                else -> Toast.makeText(
                    context,
                    resources.getString(R.string.home_toast_not_implemented),
                    Toast.LENGTH_SHORT,
                ).show()
            }
        }
        binding.fragmentHomeRecycler.adapter = adapter
    }

    private fun setStateHome() {
        viewModel.categories.onEach { homeState ->
            when (homeState) {
                is State.Loading -> {
                    showStateLoading()
                }
                is State.Empty -> {
                    showStateEmpty()
                }
                is State.Error -> {
                    showStateError()
                }
                is State.Success -> {
                    showStateSuccess(homeState.result)
                }
            }
        }.launchIn(lifecycleScope)
    }

    private fun showStateLoading() {
        binding.fragmentHomeShimmer.startShimmer()
    }

    private fun showStateEmpty() {
        stopShimmer()
        showTextEmpty(message = resources.getString(R.string.home_empty_categories))
        Toast.makeText(context, "empty", Toast.LENGTH_SHORT).show()
    }

    private fun showStateError() {
        stopShimmer()
        showTextEmpty(message = resources.getString(R.string.home_error_categories))
        Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
    }

    private fun showStateSuccess(categories: List<Category>) {
        stopShimmer()
        showTextEmpty(show = false)
        adapter.submitList(categories)
    }

    private fun showTextEmpty(message: String? = null, show: Boolean = true) {
        if (show) {
            binding.fragmentHomeTextMessage.visibility = View.VISIBLE
            binding.fragmentHomeTextMessage.text = message
        } else {
            binding.fragmentHomeTextMessage.visibility = View.GONE
        }
    }

    private fun stopShimmer() {
        binding.fragmentHomeShimmer.stopShimmer()
        binding.fragmentHomeShimmer.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        timber.log.Timber.d("onDestroyView")
    }
}
