package cz.mendelu.pef.va1.xmichl.golf.di

import cz.mendelu.pef.va1.xmichl.golf.ui.screens.AddViewModel
import cz.mendelu.pef.va1.xmichl.golf.ui.screens.ListResultViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ListResultViewModel(get()) }
    viewModel { AddViewModel(get()) }
}