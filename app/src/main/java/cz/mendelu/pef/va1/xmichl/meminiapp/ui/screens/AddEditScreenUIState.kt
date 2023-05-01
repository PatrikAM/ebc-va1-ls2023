package cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens

sealed class AddEditScreenUIState {
    object Default : AddEditScreenUIState()
    object MemorySaved : AddEditScreenUIState()
    object MemoryChanged : AddEditScreenUIState()
    object Loading : AddEditScreenUIState()
}
