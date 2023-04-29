package cz.mendelu.pef.va1.xmichl.meminiapp.di

import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.memoryList.MemoryListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MemoryListViewModel(get()) }
}
