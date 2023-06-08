package cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens

sealed class SearchUIState {
    object Default: SearchUIState()
    object MonthSelected: SearchUIState()
    object TitleChanged: SearchUIState()
    object MemoriesSearched: SearchUIState()
}