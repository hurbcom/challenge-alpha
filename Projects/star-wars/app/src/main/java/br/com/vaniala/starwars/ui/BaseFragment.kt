package br.com.vaniala.starwars.ui

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import br.com.vaniala.starwars.databinding.FragmentGridBinding
import java.lang.reflect.ParameterizedType

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 13/04/23.
 *
 */

abstract class BaseFragment<VM : ViewModel> : Fragment() {

    protected lateinit var viewModel: VM

    private var _binding: FragmentGridBinding? = null
    val binding: FragmentGridBinding get() = _binding!!

    private val type = (javaClass.genericSuperclass as ParameterizedType)
    private val classVM = type.actualTypeArguments[0] as Class<VM>
    private lateinit var view: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewModel = createViewModelLazy(classVM.kotlin, { viewModelStore }).value
        _binding = FragmentGridBinding
            .inflate(
                inflater,
                container,
                false,
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.view = view
        initViews()
        initButtonBack()
        initListeners()
        initAdapter()
    }

    private fun initListeners() {
        binding.apply {
            fragmentsGridSearchEditText.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    filterPaging()
                    return@setOnEditorActionListener true
                }
                return@setOnEditorActionListener false
            }

            fragmentsGridSearchEditText.setOnKeyListener { _, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    filterPaging()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }
    }

    private fun filterPaging() {
        hideKeyboard(view)
        binding.fragmentsGridSearchEditText.text?.trim().let { search ->
            if (search != null) {
                pagingFilter(search)
            }
        }
    }

    private fun initButtonBack() {
        binding.fragmentGridImgButtonBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    fun stopShimmer() {
        if (!binding.fragmentGridShimmer.isVisible) {
            binding.fragmentGridShimmer.stopShimmer()
        } else {
            binding.fragmentGridShimmer.startShimmer()
        }
    }

    private fun hideKeyboard(view: View) {
        val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onDestroyView() {
        binding.fragmentGridRecycler.adapter = null
        _binding = null
        timber.log.Timber.d("onDestroyView")
        super.onDestroyView()
    }

    abstract fun initViews()
    abstract fun initAdapter()
    open fun pagingFilter(search: CharSequence) {}
}
