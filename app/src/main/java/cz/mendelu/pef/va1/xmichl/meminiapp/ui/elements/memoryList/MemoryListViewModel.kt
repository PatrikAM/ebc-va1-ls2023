package cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.memoryList

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import cz.mendelu.pef.va1.xmichl.meminiapp.architecture.BaseViewModel
import cz.mendelu.pef.va1.xmichl.meminiapp.database.IMemoriesRepository
import kotlinx.coroutines.launch

class MemoryListViewModel(
    private val repository: IMemoriesRepository
    )
    : BaseViewModel(), MemoryListActions
    {

    val memoryListUIState: MutableState<MemoryListUIState> =
        mutableStateOf(MemoryListUIState.Default)

    fun loadMemories(){
        launch {
//            repository.getAll().collect {
//                memoryListUIState.value = MemoryListUIState.Success(it)
//            }
        }
    }


}
