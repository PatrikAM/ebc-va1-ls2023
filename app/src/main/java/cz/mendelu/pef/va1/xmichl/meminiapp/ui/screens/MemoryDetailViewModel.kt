package cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import cz.mendelu.pef.va1.xmichl.meminiapp.architecture.BaseViewModel
import cz.mendelu.pef.va1.xmichl.meminiapp.database.IMemoriesRepository
import kotlinx.coroutines.launch

class MemoryDetailViewModel(
    private val repository: IMemoriesRepository
) : BaseViewModel(), MemoryDetailActions {

    var memoryId: Long = -1
    var data: MemoryDetailScreenData = MemoryDetailScreenData()

    val memoryDetailUIState: MutableState<MemoryDetailUIState> =
        mutableStateOf(MemoryDetailUIState.Loading)

    override fun deleteMemory() {
        launch {
            repository.deleteMemory(data.memory)
            memoryDetailUIState.value = MemoryDetailUIState.MemoryDeleted
        }
    }

    fun initMemory() {
        launch {
            Log.d("memid", "$memoryId")
            data.memory = repository.getMemoryById(memoryId)
            data.loading = false
            memoryDetailUIState.value = MemoryDetailUIState.MemoryChanged
        }
    }

}