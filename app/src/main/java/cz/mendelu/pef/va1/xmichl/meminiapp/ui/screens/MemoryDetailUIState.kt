package cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens

sealed class MemoryDetailUIState {
    object Default : MemoryDetailUIState()
    object MemoryDeleted : MemoryDetailUIState()
    object MemoryChanged : MemoryDetailUIState()
    object Loading : MemoryDetailUIState()
}