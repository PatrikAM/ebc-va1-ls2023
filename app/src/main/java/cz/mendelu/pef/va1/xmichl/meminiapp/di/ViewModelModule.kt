package cz.mendelu.pef.va1.xmichl.meminiapp.di

import cz.mendelu.pef.va1.xmichl.meminiapp.ui.activities.MainActivityViewModel
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.activities.SplashScreenActivityViewModel
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.memoryList.MemoryListViewModel
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens.AddEditMemoryViewModel
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens.MapPickerPickerViewModel
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens.MemoryDetailViewModel
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens.SearchViewModel
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MemoryListViewModel(get()) }
    viewModel { AddEditMemoryViewModel(get()) }
    viewModel { MapPickerPickerViewModel() }
    viewModel { MemoryDetailViewModel(get()) }
    viewModel { SearchViewModel() }
    viewModel { SettingsViewModel() }
    viewModel { SplashScreenActivityViewModel() }
    viewModel { MainActivityViewModel() }
}
