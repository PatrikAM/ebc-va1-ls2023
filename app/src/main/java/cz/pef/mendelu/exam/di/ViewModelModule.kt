package cz.pef.mendelu.exam.di

import cz.pef.mendelu.exam.ui.screens.AddCounterViewModel
import cz.pef.mendelu.exam.ui.screens.CounterListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CounterListViewModel(get()) }
    viewModel { AddCounterViewModel(get()) }
}