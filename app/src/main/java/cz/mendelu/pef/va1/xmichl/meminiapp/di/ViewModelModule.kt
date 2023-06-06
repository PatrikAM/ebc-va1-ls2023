package cz.mendelu.pef.va1.xmichl.meminiapp.di

import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.memoryList.MemoryListViewModel
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens.AddEditMemoryViewModel
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens.MapScreenViewModel
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens.MemoryDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MemoryListViewModel(get()) }
    viewModel { AddEditMemoryViewModel(get()) }
    viewModel { MapScreenViewModel() }
    viewModel { MemoryDetailViewModel(get()) }
}
