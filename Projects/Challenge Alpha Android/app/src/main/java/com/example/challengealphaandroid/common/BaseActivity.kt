package com.example.challengealphaandroid.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<VB: ViewBinding, VM: BaseViewModel<*>>(private val inflate: Inflate<VB>): AppCompatActivity() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    protected lateinit var viewModel: VM
        private set


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        _binding = inflate.invoke(layoutInflater, null, false)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(getViewModelClass())
        setUpUi()
        observeData()
    }

    private fun getViewModelClass(): Class<VM> = (
            (javaClass.genericSuperclass as ParameterizedType)
                .actualTypeArguments[1] as Class<VM>
            )

    protected abstract fun observeData()

    protected abstract fun setUpUi()
}