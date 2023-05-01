package cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.memoryList

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cz.mendelu.pef.va1.xmichl.meminiapp.R
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

    if (memories.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Spacer(modifier = Modifier.height(150.dp))
                Image(
                    painter = painterResource(id = R.drawable.memory_list_place_holder),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize(0.5f)
                        .padding(20.dp)
                )
                Text(text = "There are no memories.")
                Text(text = "Let's go and create some!")
            }
        }
    } else {
        memories.forEach { memory ->
            MemoryRow(memory)
        }
    }


}