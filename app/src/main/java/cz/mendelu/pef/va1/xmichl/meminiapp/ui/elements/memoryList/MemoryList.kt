package cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.memoryList

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import cz.mendelu.pef.va1.xmichl.meminiapp.models.Memory
import org.koin.androidx.compose.getViewModel
import java.util.*

@Composable
fun MemoryList(
    viewModel: MemoryListViewModel = getViewModel(),
    date: Date? = null,
    today: Boolean = false,
    name: Regex = Regex(".*")
) {

    val memories = remember { mutableStateListOf<Memory>() }

    viewModel.memoryListUIState.value.let {
        when (it) {
            MemoryListUIState.Default -> {
                viewModel.loadMemories()
            }
            is MemoryListUIState.Success -> {
                memories.clear()
                memories.addAll(it.memories)
            }
            MemoryListUIState.Error -> TODO()
            MemoryListUIState.Loading -> TODO()
        }
    }


}