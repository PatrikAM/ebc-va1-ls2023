package cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import cz.mendelu.pef.va1.xmichl.meminiapp.architecture.BaseViewModel
import cz.mendelu.pef.va1.xmichl.meminiapp.database.IMemoriesRepository
import kotlinx.coroutines.launch

class AddEditMemoryViewModel(private val repository: IMemoriesRepository)
    : BaseViewModel(), AddEditMemoryActions {

    var memoryId: Long? = null
    var data: AddEditScreenData = AddEditScreenData()


    val addEditMemoryUIState: MutableState<AddEditScreenUIState> =
        mutableStateOf(AddEditScreenUIState.Loading)

    override fun saveMemory() {
        if (data.memory.title.isEmpty()) {
            // error
            // TODO check date and primary photo also
            addEditMemoryUIState.value = AddEditScreenUIState.MemoryChanged
        } else {
            launch {
                if (memoryId == null){
                    val id = repository.insert(data.memory)
                    if (id > 0) {
                        addEditMemoryUIState.value = AddEditScreenUIState.MemorySaved
                    } else {
                        // error
                    }
                } else {
                    repository.update(data.memory)
                    addEditMemoryUIState.value = AddEditScreenUIState.MemorySaved
                }
            }
        }
    }

    override fun onTitleChanged(title: String) {
        data.memory.title = title
        addEditMemoryUIState.value = AddEditScreenUIState.MemoryChanged
    }

    override fun onDateChanged(date: Long?) {
        data.memory.date = date
        addEditMemoryUIState.value = AddEditScreenUIState.MemoryChanged
    }

    fun initMemory() {
        if (memoryId != null){
            launch {
                data.memory = repository.getMemoryById(memoryId!!)
                data.loading = false
                addEditMemoryUIState.value = AddEditScreenUIState.MemoryChanged
            }
        } else {
            data.loading = false
            addEditMemoryUIState.value = AddEditScreenUIState.MemoryChanged
        }
    }

    override fun onDescriptionChanged(desc: String) {
        data.memory.description = desc
    }
}
