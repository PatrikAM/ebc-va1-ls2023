package cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.memoryList

import cz.mendelu.pef.va1.xmichl.meminiapp.models.Memory

sealed class MemoryListUIState {
    object Default : MemoryListUIState()
    object Error : MemoryListUIState()
    class Success(val memories: List<Memory>) : MemoryListUIState()
    object Loading : MemoryListUIState()
}
