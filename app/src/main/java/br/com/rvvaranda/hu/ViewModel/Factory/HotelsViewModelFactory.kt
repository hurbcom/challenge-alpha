package br.com.rvvaranda.hu.ViewModel.Factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.rvvaranda.hu.Repository.HotelsRepository
import br.com.rvvaranda.hu.ViewModel.HotelsViewModel

class HotelsViewModelFactory (private val repository: HotelsRepository, private val page: Int) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HotelsViewModel() as T
    }
}