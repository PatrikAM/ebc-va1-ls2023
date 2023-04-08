package cz.mendelu.pef.va1.xmichl.homework2.di

import cz.mendelu.pef.va1.xmichl.homework2.ui.screens.ContactListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ContactListViewModel(get()) }
}