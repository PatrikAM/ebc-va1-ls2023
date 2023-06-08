package cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.memoryList

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import cz.mendelu.pef.va1.xmichl.meminiapp.R
import cz.mendelu.pef.va1.xmichl.meminiapp.models.Memory
import cz.mendelu.pef.va1.xmichl.meminiapp.navigation.INavigationRouter
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.PlaceHolderScreen
import org.koin.androidx.compose.getViewModel
import java.util.*

@Composable
fun MemoryList(
    viewModel: MemoryListViewModel = getViewModel(),
    navigation: INavigationRouter,
    memoryFilter: (Memory) -> Boolean = { true },
    placeholderImage: Int = R.drawable.memory_list_place_holder,
    placeholderFstLine: String = stringResource(id = R.string.sadly_no_memory_in_here),
    placeholderSndLine: String = stringResource(R.string.let_s_go_and_create_some)

) {

    val memories = remember { mutableStateListOf<Memory>() }

    viewModel.memoryListUIState.value.let {
        when (it) {
            MemoryListUIState.Default -> {
                viewModel.loadMemories()
            }
            is MemoryListUIState.Success -> {
                memories.clear()
                memories.addAll(it.memories.filter { memory -> memoryFilter(memory) })
            }
            MemoryListUIState.Error -> TODO()
            MemoryListUIState.Loading -> TODO()
        }
    }

    if (memories.isEmpty()) {
        PlaceHolderScreen(
            image = placeholderImage,
            text = placeholderFstLine,
            subtext = placeholderSndLine
        )
    } else {
        memories.forEach { memory ->
            MemoryRow(memory) {
                navigation.navigateToMemoryDetailScreen(memory.id!!)
            }
        }
    }
}