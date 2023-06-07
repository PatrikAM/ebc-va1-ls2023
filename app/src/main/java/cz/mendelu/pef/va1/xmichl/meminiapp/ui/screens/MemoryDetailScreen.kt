package cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens

import Carousel
import ConfirmationAlertDialog
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import cz.mendelu.pef.va1.xmichl.meminiapp.R
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

    val confirmDialogPresented = remember { mutableStateOf(false) }

    val data: MemoryDetailScreenData by remember {
        mutableStateOf(viewModel.data)
    }

    BackArrowScreen(
        appBarTitle = viewModel.data.memory.title, //TODO: title
        onBackClick = {
            navigation.returnBack()
        },
        actions = {
            IconButton(onClick = {
                navigation.navigateAddEditMemoryScreen(id)
            }) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "")
            }
            IconButton(onClick = {
                confirmDialogPresented.value = true
            }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "")
            }
        }
    ) {
//        DetailScreenContent(
//            actions = viewModel,
//            data = data
//        )

        if (confirmDialogPresented.value) {
            ConfirmationAlertDialog(
                message = stringResource(R.string.cofrimation_message),
                onDismiss = {},
                //actionLabel = stringResource(R.string.yes),
                onConfirm = {
                    viewModel.deleteMemory()
                    confirmDialogPresented.value = false
                }
            )
        }
        Carousel(items = data.memory.getImages())
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(modifier = Modifier.fillMaxSize(0.8F)) {
                Text("")
                Text(text = DateUtils.getDateString(data.memory.date))
                Text("")
                Text(
                    text = if (data.memory.hasLocation())
                        if (Location(
                                data.memory.latitude!!,
                                data.memory.longitude
                            ).getNearestCity() != null
                        )
                            "${
                                Location(
                                    data.memory.latitude!!,
                                    data.memory.longitude
                                ).getNearestCity()
                            }"
                        else
                            "${data.memory.latitude!!.round()}; ${data.memory.longitude!!.round()}"
                    else ""
                )

                if (data.memory.hasLocation()) {
                    Text("")
                }

                Text(
                    text = if (data.memory.description == null) "" else data.memory.description!!,
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}

@Composable
fun DetailScreenContent(
    actions: MemoryDetailActions,
    data: MemoryDetailScreenData
) {
    if (data.confirmDialogPresented) {
        ConfirmationAlertDialog(
            message = stringResource(R.string.cofrimation_message),
            onDismiss = {},
            //actionLabel = stringResource(R.string.yes),
            onConfirm = {
                actions.deleteMemory()
                data.confirmDialogPresented = false
            }
        )
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.fillMaxSize(0.8F)) {

            Text(text = DateUtils.getDateString(data.memory.date))
            Text(text = data.memory.primaryPhoto)
            Text("")
            Text(
                text = if (data.memory.hasLocation())
                    if (Location(
                            data.memory.latitude!!,
                            data.memory.longitude
                        ).getNearestCity() != null
                    )
                        "${
                            Location(
                                data.memory.latitude!!,
                                data.memory.longitude
                            ).getNearestCity()
                        }"
                    else
                        "${data.memory.latitude!!.round()}; ${data.memory.longitude!!.round()}"
                else ""
            )

            Text(
                text = if (data.memory.description == null) "" else data.memory.description!!,
                textAlign = TextAlign.Justify
            )
        }
    }
}