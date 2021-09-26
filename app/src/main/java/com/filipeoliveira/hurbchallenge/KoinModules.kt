package com.filipeoliveira.hurbchallenge

import com.filipeoliveira.hurbchallenge.ui.list.HotelListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modules = module {
    viewModel { HotelListViewModel() }
}