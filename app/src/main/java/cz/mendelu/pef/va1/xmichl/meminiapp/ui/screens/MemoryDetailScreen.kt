package cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import cz.mendelu.pef.va1.xmichl.meminiapp.extensions.round
import cz.mendelu.pef.va1.xmichl.meminiapp.models.Location
import cz.mendelu.pef.va1.xmichl.meminiapp.navigation.INavigationRouter
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.screenSkeletons.BackArrowScreen
import cz.mendelu.pef.va1.xmichl.meminiapp.utils.DateUtils
import org.koin.androidx.compose.getViewModel

@Composable
fun MemoryDetailScreen(
    navigation: INavigationRouter,
    viewModel: MemoryDetailViewModel = getViewModel(),
    id: Long
) {

    viewModel.memoryId = id
    viewModel.memoryDetailUIState.value.let {
        when (it) {
            MemoryDetailUIState.Default -> {}
            MemoryDetailUIState.Loading -> {

                viewModel.initMemory()
            }

            MemoryDetailUIState.MemoryChanged -> {}
            MemoryDetailUIState.MemoryDeleted -> {
                LaunchedEffect(it) {
                    navigation.returnBack()
                }
            }
        }
    }

    BackArrowScreen(
        appBarTitle = viewModel.data.memory.title, //TODO: title
        onBackClick = {
            navigation.returnBack()
        },
        actions = {
            IconButton(onClick = { navigation.navigateAddEditMemoryScreen(id) }) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "")
            }
            IconButton(onClick = { viewModel.deleteMemory() }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "")
            }
        }
    ) {

        Column(modifier = Modifier.fillMaxSize(0.8F)) {
            Text(text = DateUtils.getDateString(viewModel.data.memory.date))
            Text(text = viewModel.data.memory.primaryPhoto)
            Text(
                text = if (viewModel.data.memory.hasLocation())
                    if (Location(
                            viewModel.data.memory.latitude!!,
                            viewModel.data.memory.longitude
                        ).getNearestCity() != null
                    )
                        "${
                            Location(
                                viewModel.data.memory.latitude!!,
                                viewModel.data.memory.longitude
                            ).getNearestCity()
                        }"
                    else
                        "${viewModel.data.memory.latitude!!.round()}; ${viewModel.data.memory.longitude!!.round()}"
                else ""
            )
            Text(
                text = if (viewModel.data.memory.description == null) "" else viewModel.data.memory.description!!,
                textAlign = TextAlign.Justify
            )
        }

    }
}